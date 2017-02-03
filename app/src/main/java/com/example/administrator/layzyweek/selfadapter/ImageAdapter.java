package com.example.administrator.layzyweek.selfadapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/16.
 */

public class ImageAdapter extends PagerAdapter {
    private List<ImageView> mlist;
    public ImageAdapter(List<ImageView> list){
        this.mlist = list;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mlist.size();
        if(position<0){
            position = mlist.size()+position;
        }
        ImageView imageView = mlist.get(position);
        ViewParent parent = imageView.getParent();
        if(parent!=null){
            ViewGroup vg = (ViewGroup) parent;
            vg.removeView(imageView);
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
