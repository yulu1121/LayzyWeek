package com.example.administrator.layzyweek.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.fragments.FirstFragment;
import com.example.administrator.layzyweek.fragments.FourthFragment;
import com.example.administrator.layzyweek.fragments.SecondFragment;
import com.example.administrator.layzyweek.fragments.ThirdFragment;
import com.example.administrator.layzyweek.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private ViewPager mViewPager;
    private AlertDialog dialog;
    private LayoutInflater inflater;
    private List<View> thirdAdList;//存放广告视图的集合
    private ViewPager thirdAdViewPager;
    private FirstFragment firstFragment = new FirstFragment();
    private SecondFragment secondFragment = new SecondFragment();
    private ThirdFragment thirdFragment = new ThirdFragment();
    private FourthFragment fourthFragment = new FourthFragment();
    public LocationClient mLocationClient = null;
    private List<Fragment> mlist;
//    public static final String LOCATION = "location";
//    private LocalBroadcastManager manager;
    private RadioButton selectFirst;
    private RadioButton selectSecond;
    private RadioButton selectThird;
    private RadioButton selectFourth;
    private LinearLayout dotLinear;//圆点的线性布局
    private List<ImageView> dotList;//用于存放圆点的集合
    private int currentDot = 0;
    private List<RadioButton> radioButtonList;
    private int currentRadion = 0;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        manager = LocalBroadcastManager.getInstance(this);
//        IntentFilter intentFilter = new IntentFilter(LOCATION);
//        manager.registerReceiver(receiver,intentFilter);
//        Intent intent = new Intent(this, MapService.class);
//        startService(intent);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener( myListener );//注册监听函数
        initLocation();
        mLocationClient.start();//声明LocationClient类
        setContentView(R.layout.activity_main);
        initFragments();
        initView();
    }
//    private BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if(LOCATION.equals(intent.getAction())){
//                Bundle location = intent.getBundleExtra("location");
//                double latitude = location.getDouble("latitude");
//                Log.e("==","xx"+latitude);
//                SharedPreferenceUtils.saveFloat(MainActivity.this,"latitude",(float)latitude);
//                double longitude = location.getDouble("longitude");
//                SharedPreferenceUtils.saveFloat(MainActivity.this,"longitude",(float)longitude);
//            }
//        }
//    };
    //初始化圆点集合
    private void initDots(int count){
        dotList = new ArrayList<>();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5,0,5,0);
        for (int i = 0; i < count; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.dot_selector);
            dotList.add(imageView);
            dotLinear.addView(imageView,lp);
        }
        //默认第一个被选中
        dotList.get(0).setEnabled(false);
    }
    //初始化广告对话窗
    private void initDialog(){
        initThirdViewPager();
        View view = inflater.inflate(R.layout.third_ad_layout,null);
        thirdAdViewPager = (ViewPager) view.findViewById(R.id.third_ad_viewPager);
        dotLinear = (LinearLayout) view.findViewById(R.id.dot_layout);
        thirdAdViewPager.setAdapter(new ThirdAdAdapter());
        initDots(thirdAdList.size());
        thirdAdViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //将原来的页面设为未选中状态
                    dotList.get(currentDot).setEnabled(true);
                //讲现在的界面设为选中状态
                    dotList.get(position).setEnabled(false);
                    currentDot = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        dialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
    }
    //初始化广告的界面
    private void initThirdViewPager(){
        inflater = LayoutInflater.from(this);
        thirdAdList = new ArrayList<>();
        View view1 = inflater.inflate(R.layout.third_ad_item,null);
        ImageView adImage1 = (ImageView) view1.findViewById(R.id.third_ad_guid);
        TextView adMainText1 = (TextView) view1.findViewById(R.id.third_ad_text);
        TextView adExtraText1 = (TextView) view1.findViewById(R.id.third_ad_extraText);
        adImage1.setBackgroundResource(R.mipmap.guide1);
        adMainText1.setText("喵出你想");
        adExtraText1.setText("说出你的周末需求，我们来满足你要的所求");
        thirdAdList.add(view1);
        View view2 = inflater.inflate(R.layout.third_ad_item,null);
        ImageView adImage2 = (ImageView) view2.findViewById(R.id.third_ad_guid);
        TextView adMainText2 = (TextView) view2.findViewById(R.id.third_ad_text);
        TextView adExtraText2 = (TextView) view2.findViewById(R.id.third_ad_extraText);
        adMainText2.setText("懒猫订票");
        adExtraText2.setText("欢乐票务我来帮你订，你就是世界的中心");
        adImage2.setImageResource(R.mipmap.guide2);
        thirdAdList.add(view2);
        View view3 = inflater.inflate(R.layout.third_ad_item_last,null);
        ImageView adImage3 = (ImageView) view3.findViewById(R.id.third_ad_guid);
        TextView adMainText3 = (TextView) view3.findViewById(R.id.third_ad_text);
        TextView adExtraText3= (TextView) view3.findViewById(R.id.third_ad_extraText);
        adImage3.setImageResource(R.mipmap.guide3);
        adMainText3.setText("多人团建");
        adExtraText3.setText("专属定制，个性出游尽享惊喜之旅");
        thirdAdList.add(view3);
       }

    public void onClickEndDialog(View view) {
        dialog.dismiss();
    }

    public void onClickEndViewPager(View view) {
        dialog.dismiss();
    }

    class ThirdAdAdapter extends PagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(thirdAdList.get(position));
            return thirdAdList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(thirdAdList.get(position));
        }

        @Override
        public int getCount() {
            return null==thirdAdList?0:thirdAdList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
    private void initFragments(){
        mlist = new ArrayList<>();
        mlist.clear();
        mlist.add(firstFragment);
        mlist.add(secondFragment);
        mlist.add(thirdFragment);
        mlist.add(fourthFragment);
    }
    private void initView() {
        radioButtonList = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        selectFirst = (RadioButton) findViewById(R.id.selectOne);
        selectFirst.setOnClickListener(this);
        radioButtonList.add(selectFirst);
        selectSecond = (RadioButton) findViewById(R.id.selectTwo);
        selectSecond.setOnClickListener(this);
        radioButtonList.add(selectSecond);
        selectThird = (RadioButton) findViewById(R.id.selectThree);
        selectThird.setOnClickListener(this);
        radioButtonList.add(selectThird);
        selectFourth = (RadioButton) findViewById(R.id.selectFour);
        selectFourth.setOnClickListener(this);
        radioButtonList.add(selectFourth);
        mViewPager.setAdapter(new ViewPageFragmentAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radioButtonList.get(currentRadion).setChecked(false);
                radioButtonList.get(position).setChecked(true);
                currentRadion = position;
                Fragment fragment = mlist.get(position);
                if(fragment==(thirdFragment)){
                        initThirdViewPager();
                        initDialog();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        manager.unregisterReceiver(receiver);
        mLocationClient.stop();

    }

    //初始化位置
    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span=0;
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.selectOne:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.selectTwo:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.selectThree:
                mViewPager.setCurrentItem(2);
                initThirdViewPager();
                initDialog();
                dialog.show();
                break;
            case R.id.selectFour:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
           double latitude = location.getLatitude();
            SharedPreferenceUtils.saveFloat(MainActivity.this,"latitude",(float)latitude);
           double longitude = location.getLongitude();
            SharedPreferenceUtils.saveFloat(MainActivity.this,"longitude",(float)longitude);
           String city = location.getAddress().city;
            SharedPreferenceUtils.saveString(MainActivity.this,"city",city);
        }
    }
    class ViewPageFragmentAdapter extends FragmentPagerAdapter{

        public ViewPageFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public int getCount() {
            return null==mlist?0:mlist.size();
        }
    }

}
