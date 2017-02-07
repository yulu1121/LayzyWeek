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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.activities.firstformationdragger.DaggerFirstAppComponent;
import com.example.administrator.layzyweek.activities.firstformationdragger.FirstAppMoudel;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenter;
import com.example.administrator.layzyweek.entries.FirstPageDetail;
import com.example.administrator.layzyweek.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页详情的页面
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationActivity extends BaseActivity implements FirstFormationPresenter.FirstFormationSend2View{
    @BindView(R.id.first_formation_pager)
    ViewPager pager;
    @BindView(R.id.first_formation_title)
    TextView title;
    @BindView(R.id.first_formation_kindName)
    TextView category;
    @BindView(R.id.first_foramtion_price)
    TextView price;
    @BindView(R.id.first_formation_opentime)
    TextView openTime;
    @BindView(R.id.first_formation_kindImage)
    ImageView kindImage;
    @BindView(R.id.first_formation_address)
    TextView address;
    @BindView(R.id.linearFormation)
    LinearLayout linear;
    @BindView(R.id.lazyNotify_linear)
    LinearLayout lazyNotifyLinear;
    @BindView(R.id.booking_linear)
    LinearLayout bookingLinear;
    private List<ImageView> imageList;
    private Handler mHandler;
    private int currentPager = 0;
    Bundle bundle  = null;
    @Inject
    FirstFormationPresenter presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Intent intent=getIntent();
        bundle = intent.getBundleExtra("formation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page_formation);
        DaggerFirstAppComponent.builder().firstAppMoudel(new FirstAppMoudel(this)).build().inJect(this);
        presenter.firstFormationGetData(String.valueOf(bundle.getInt("leo_id")));
        ButterKnife.bind(this);
    }
    private void initPager(List<String> list){
        imageList = new ArrayList<>();
        for (int i = 0; i < 3 ; i++) {
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

    public void onClickBackIng(View view) {
        finish();
    }

    @Override
    public void sendFormation2View(FirstPageDetail detail) {
        FirstPageDetail.ResultBean result = detail.getResult();
        List<String> images = result.getImages();
        initPager(images);
        pager.setAdapter(new ViewPagerAdapter());
        if(mHandler==null){
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(currentPager==2){
                        currentPager=0;
                    }else {
                        currentPager++;
                    }
                    pager.setCurrentItem(currentPager,false);
                    mHandler.postDelayed(this,3000);
                }
            }, 3000);
        }
        address.setText(result.getAddress());
        price.setText("￥"+result.getPrice_info());
        title.setText(result.getTitle());
        category.setText(result.getCategory().getCn_name());
        final String kindImageUrl  = result.getCategory().getIcon_view();
        kindImage.setTag(kindImageUrl);
        ImageLoader.loadImage(kindImageUrl, new ImageLoader.ImageListener() {
            @Override
            public void ImageComplete(Bitmap bitMap, String Url) {
                if(Url.equals(kindImage.getTag())){
                    kindImage.setImageBitmap(bitMap);
                }
            }
        });
        openTime.setText(result.getTime().getInfo());
        List<FirstPageDetail.ResultBean.DescriptionBean> description = result.getDescription();
        for (int i = 0; i <description.size(); i++) {
            if(description.get(i).getType().equals("text")){
                TextView textView  = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setText(description.get(i).getContent());
                linear.addView(textView);
            }else if(description.get(i).getType().equals("image")){
                final ImageView imageView = new ImageView(this);
                String url = description.get(i).getContent();
                imageView.setTag(url);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
                imageView.setLayoutParams(lp);
                ImageLoader.loadImage(url, new ImageLoader.ImageListener() {
                    @Override
                    public void ImageComplete(Bitmap bitMap, String Url) {
                        if(Url.equals(imageView.getTag())){
                            imageView.setImageBitmap(bitMap);
                        }
                    }
                });
                linear.addView(imageView);
            }
        }
        List<FirstPageDetail.ResultBean.LrzmTipsBean> lrzmTips = result.getLrzm_tips();
        for (int i = 0; i < lrzmTips.size() ; i++) {
            if(lrzmTips.get(i).getType().equals("text")){
                TextView textView = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setText(lrzmTips.get(i).getContent());
                lazyNotifyLinear.addView(textView);
            }
        }
        List<FirstPageDetail.ResultBean.BookingPolicyBean> bookingPolicy = result.getBooking_policy();
        for (int i = 0; i < bookingPolicy.size(); i++) {
            if(bookingPolicy.get(i).getType().equals("text")){
                TextView textView = new TextView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(lp);
                textView.setText(bookingPolicy.get(i).getContent());
                bookingLinear.addView(textView);
            }
        }
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
