package com.example.administrator.layzyweek.selfview;

import android.support.v4.view.ViewPager;

/**
 *
 * Created by Administrator on 2017/1/14.
 */

public class MyPagechaneListener implements ViewPager.OnPageChangeListener {
    private int currentPosition;
    private ViewPager viewPager;
    private ViewPagerScroller scroller;
    public MyPagechaneListener(ViewPager viewPager,ViewPagerScroller scroller){
            this.viewPager = viewPager;
            this.scroller = scroller;
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
            currentPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
