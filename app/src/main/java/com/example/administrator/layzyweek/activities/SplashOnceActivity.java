package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.selfview.ViewPagerScroller;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * Created by Administrator on 2017/1/13.
 */

public class SplashOnceActivity extends BaseActivity{
    private ViewPager viewPager;
    private  List<ImageView> imageViewList;
    private Button weiboLogin;
    private Button weixinLogin;
    private Handler mHandler;
    private int currentPager = 0;
    private  TextView textView;
    private ViewPagerScroller scroller;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_once);
        scroller  = new ViewPagerScroller(this);
//        scroller.setMscrollDuration(0);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.enter_viewPager);
        weiboLogin = (Button)findViewById(R.id.weibologin);
        weixinLogin = (Button)findViewById(R.id.weixinLogin);
        textView = (TextView) findViewById(R.id.textAd);
        initImageList();
        if(imageViewList.get(currentPager)==imageViewList.get(0)){
            textView.setText("闭目\n"+"\t难掩喜悦与期待");
        }
        viewPager.setAdapter(new SplashOncePagerAdapter());
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                final float MIN_ALPHA = 0.0f;
                int pageWidth = view.getWidth();    //得到view宽

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left. 出了左边屏幕
                    view.setAlpha(0);

                } else if (position <= 1) { // [-1,1]
                    if (position < 0) {
                        //消失的页面
                        view.setTranslationX(-pageWidth * position);  //阻止消失页面的滑动
                    } else {
                        //出现的页面
                        view.setTranslationX(pageWidth);
                        //直接设置出现的页面到底
                        view.setTranslationX(-pageWidth * position);  //阻止出现页面的滑动
                    }
                    // Fade the page relative to its size.
                    float alphaFactor = Math.max(MIN_ALPHA, 1 - Math.abs(position));
                    //透明度改变Log
                    view.setAlpha(alphaFactor);
                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.    出了右边屏幕
                    view.setAlpha(0);
                }

            }
        });
        scroller.initViewPagerScroll(viewPager);
        if(null==mHandler){
            mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(currentPager==imageViewList.size()-1){
                        currentPager = 0;
                    }else {
                        currentPager++;
                    }
                    if(imageViewList.get(currentPager)==imageViewList.get(0)){
                        textView.setText("闭目\n"+"\t难掩喜悦与期待");
                    }else if(imageViewList.get(currentPager).equals(imageViewList.get(1))){
                        textView.setText("睁眼\n"+"\t因为你心有所动");
                    }else if(imageViewList.get(currentPager).equals(imageViewList.get(2))){
                        textView.setText("启程\n"+"\t只因追寻你所爱");
                    }else if(imageViewList.get(currentPager)==imageViewList.get(3)){
                        textView.setText("我们\n"+"\t做最了解你的人");
                    }else if(imageViewList.get(currentPager)==imageViewList.get(4)){
                        textView.setText("闭目\n"+"\t难掩喜悦与期待");
                    }
                    viewPager.setCurrentItem(currentPager);
                    mHandler.postDelayed(this,3000);
                }
            }, 3000);
    }}
    private void initImageList(){
        imageViewList = new ArrayList<>();
        ImageView imageView1 = new ImageView(this);
        imageView1.setImageResource(R.mipmap.pic1);
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(imageView1);
        ImageView imageView2 = new ImageView(this);
        imageView2.setImageResource(R.mipmap.pic2);
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(imageView2);
        ImageView imageView3 = new ImageView(this);
        imageView3.setImageResource(R.mipmap.pic3);
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(imageView3);
        ImageView imageView4 = new ImageView(this);
        imageView4.setImageResource(R.mipmap.pic4);
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        imageViewList.add(imageView4);

    }
    public void onClickLoginNone(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_in,R.anim.translate_out);
        finish();
    }
    //闪屏的ViewPager的适配器
    class SplashOncePagerAdapter extends PagerAdapter{
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViewList.get(position));
            return imageViewList.get(position);
        }

        @Override
        public int getCount() {
            return null==imageViewList?0:imageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
