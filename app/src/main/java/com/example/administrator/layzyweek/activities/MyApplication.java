package com.example.administrator.layzyweek.activities;

import android.app.Application;

import com.example.administrator.layzyweek.utils.ImageUtils;

/**
 *
 * Created by Administrator on 2017/1/15.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageUtils.initCache(getApplicationContext());
    }
}
