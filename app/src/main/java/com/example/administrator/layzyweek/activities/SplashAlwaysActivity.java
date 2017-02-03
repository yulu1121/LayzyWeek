package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;

/**
 *
 * Created by Administrator on 2017/1/15.
 */

public class SplashAlwaysActivity extends BaseActivity {
    private Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_always);
        if(handler==null){
            handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashAlwaysActivity.this,SplashOnceActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },2000);
            }
        }


}
