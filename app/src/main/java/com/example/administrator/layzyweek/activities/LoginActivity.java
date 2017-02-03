package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.entries.UserNoName;
import com.example.administrator.layzyweek.utils.SharedPreferenceUtils;

/**
 * 用于登录的Activity
 * Created by Administrator on 2017/1/15.
 */

public class LoginActivity extends BaseActivity{
    private RadioButton man;
    private RadioButton woman;
    private RadioButton parent;
    private RadioButton loving;
    private RadioButton single;
    private UserNoName user;
    private Animation animation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        animation = AnimationUtils.loadAnimation(this,R.anim.sexrotate);
        LinearInterpolator interpolator = new LinearInterpolator();
        animation.setInterpolator(interpolator);
        animation.setFillAfter(true);
        initView();
        readLoginState();
    }

    private void initView() {
        man = (RadioButton) findViewById(R.id.male);
//        man.setAnimation(animation);
        woman = (RadioButton) findViewById(R.id.female);
//        woman.setAnimation(animation);
        parent = (RadioButton) findViewById(R.id.parent);
        loving = (RadioButton) findViewById(R.id.loving);
        single = (RadioButton) findViewById(R.id.single);

    }
    //存取登录状态
    private void saveLoginState(){
            if(man.isChecked()){
                SharedPreferenceUtils.saveBoolean(this,"manLoginState",true);
            }else{
                SharedPreferenceUtils.saveBoolean(this,"manLoginState",false);
            }
            if(woman.isChecked()){
                SharedPreferenceUtils.saveBoolean(this,"womanLoginState",true);
            }else {
                SharedPreferenceUtils.saveBoolean(this,"womanLoginState",false);
            }
            if(parent.isChecked()){
                SharedPreferenceUtils.saveBoolean(this,"parentLoginState",true);
            }else {
                SharedPreferenceUtils.saveBoolean(this,"parentLoginState",false);
            }
            if(loving.isChecked()){
                SharedPreferenceUtils.saveBoolean(this,"lovingLoginState",true);
            }else {
                SharedPreferenceUtils.saveBoolean(this,"lovingLoginState",false);

            }
            if(single.isChecked()){
                SharedPreferenceUtils.saveBoolean(this,"singleLoginState",true);
            }else {
                SharedPreferenceUtils.saveBoolean(this,"singleLoginState",false);
            }
    }
    //将存在sharepreference中的登录状态取出来
    private void readLoginState(){
        boolean stateMan = SharedPreferenceUtils.getBoolean(this, "manLoginState");
        man.setChecked(stateMan);
        boolean stateWoman = SharedPreferenceUtils.getBoolean(this,"womanLoginState");
        woman.setChecked(stateWoman);
        boolean stateParent = SharedPreferenceUtils.getBoolean(this,"singleLoginState");
        parent.setChecked(stateParent);
        SharedPreferenceUtils.saveString(this,"state",parent.getText().toString());
        boolean stateLoving = SharedPreferenceUtils.getBoolean(this,"lovingLoginState");
        loving.setChecked(stateLoving);
        SharedPreferenceUtils.saveString(this,"state",loving.getText().toString());
        boolean stateSingle = SharedPreferenceUtils.getBoolean(this,"singleLoginState");
        SharedPreferenceUtils.saveString(this,"state",single.getText().toString());
        single.setChecked(stateSingle);
    }
    public void onClickBack(View view) {
            Intent intent = new Intent(this,SplashOnceActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.translate_in_two,R.anim.translate_out_two);
            finish();
    }

    public void onClickNext(View view) {
        if(!((man.isChecked()||woman.isChecked())&&(parent.isChecked()||loving.isChecked()
                ||single.isChecked()))){
            Toast.makeText(this, "为能给您推荐适合的产品，请选择个人状态哦", Toast.LENGTH_SHORT).show();
        }else {
            saveLoginState();
            Intent intent  = new Intent(this,HobbyActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.translate_in,R.anim.translate_out);
        }
    }
}
