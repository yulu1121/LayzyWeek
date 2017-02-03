package com.example.administrator.layzyweek.selfview;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 *  A页面，-1，小于等于0///B页面0,小于等于1
 * Created by Administrator on 2017/1/13.
 */

public class DepthPagerTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View page, float position) {
        int pageWith = page.getWidth();
        if(position<-1){
            //小于-1,说明不显示
            page.setAlpha(0);
        }
    }
}
