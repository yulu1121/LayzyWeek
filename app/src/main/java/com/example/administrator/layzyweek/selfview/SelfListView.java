package com.example.administrator.layzyweek.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 *
 * Created by Administrator on 2017/1/17.
 */

public class SelfListView extends ListView{
    public SelfListView(Context context) {
       this(context,null);
    }

    public SelfListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
