package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenter;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenter.FirstFormationSend2View;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenterImpl;
import com.example.administrator.layzyweek.entries.FirstPageFormation;
import com.example.administrator.layzyweek.utils.ImageLoader;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页详情的页面
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationActivity extends BaseActivity implements FirstFormationSend2View{
    private ViewPager pager;
    private TextView title;
    private TextView category;
    private TextView price;
    private TextView openTime;
    private ImageView imageOne;
    private ImageView imageTwo;
    private ImageView imageThree;
    private TextView address;
    private List<ImageView> imageList;
    private Handler mHandler;
    private int currentPager = 0;
    Bundle bundle  = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent=getIntent();
        bundle = intent.getBundleExtra("formation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page_formation);
        FirstFormationPresenter presenter = new FirstFormationPresenterImpl(this);
        presenter.firstFormationGetData(String.valueOf(bundle.getInt("leo_id")));
        initView();
    }
    private void initPager(ArrayList<String> list){
        imageList = new ArrayList<>();
        for (int i = 0; i < list.size() ; i++) {
            final ImageView imageView = new ImageView(this);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageLoader.loadImage(list.get(i), new ImageLoader.ImageListener() {
                @Override
                public void ImageComplete(Bitmap bitMap, String Url) {
                    imageView.setImageBitmap(bitMap);
                }
            });
            imageList.add(imageView);
        }
    }
    private void initView() {
        pager = (ViewPager) findViewById(R.id.first_formation_pager);
        title = (TextView) findViewById(R.id.first_formation_title);
        category = (TextView) findViewById(R.id.first_formation_kindName);
        price = (TextView)findViewById(R.id.first_foramtion_price);
        openTime = (TextView)findViewById(R.id.first_formation_opentime);
        address = (TextView) findViewById(R.id.first_formation_address);
        imageOne = (ImageView)findViewById(R.id.first_formation_imageOne);
        imageTwo = (ImageView)findViewById(R.id.first_formation_imageTwo);
        imageThree = (ImageView)findViewById(R.id.first_formation_imageThree);
        title.setText(bundle.getString("title"));
        category.setText(bundle.getString("category"));
        price.setText("￥"+bundle.getString("price"));
        openTime.setText(bundle.getString("overtime"));
        address.setText(bundle.getString("address"));
        ArrayList<String> image = bundle.getStringArrayList("image");
        initPager(image);
        pager.setAdapter(new ViewPagerAdapter());
        if(mHandler==null){
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(currentPager==imageList.size()-1){
                        currentPager=0;
                    }else {
                        currentPager++;
                    }
                    pager.setCurrentItem(currentPager,false);
                    mHandler.postDelayed(this,3000);
                }
            }, 3000);
        }
        String urlOne = image.get(0);
        imageOne.setTag(urlOne);
        ImageLoader.loadImage(urlOne, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(imageOne.getTag())){
                    imageOne.setImageBitmap(bitMap);
                }
            }
        });
        String urlTwo = image.get(1);
        imageTwo.setTag(urlTwo);
        ImageLoader.loadImage(urlTwo, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(imageTwo.getTag())){
                    imageTwo.setImageBitmap(bitMap);
                }
            }
        });
        String urlThree = image.get(2);
        imageThree.setTag(urlThree);
        ImageLoader.loadImage(urlThree, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(imageThree.getTag())){
                    imageThree.setImageBitmap(bitMap);
                }
            }
        });
    }

    @Override
    public void sendFormation2View(String data) {
        Gson gson = new Gson();
        FirstPageFormation formation = gson.fromJson(data, FirstPageFormation.class);

    }

    public void onClickBackIng(View view) {
        finish();
    }
    class ViewPagerAdapter extends PagerAdapter{
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageList.get(position));
            return imageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageList.get(position));
        }

        @Override
        public int getCount() {
            return null==imageList?0:imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
