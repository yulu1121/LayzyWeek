package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.utils.SharedPreferenceUtils;

/**
 *
 * Created by Administrator on 2017/1/16.
 */

public class HobbyActivity extends BaseActivity {
    private CheckBox hobbyTravel;
    private CheckBox hobbyBar;
    private CheckBox hobbyMusic;
    private CheckBox hobbyThreater;
    private CheckBox hobbyPicture;
    private CheckBox hobbyFood;
    private CheckBox hobbyShopping;
    private CheckBox hobbyMovie;
    private CheckBox hobbyParty;
    private CheckBox hobbySport;
    private CheckBox hobbyPublic;
    private CheckBox hobbyBusinness;
//    private List<CheckBox> mlist;
//    private GridView gridView;
//    private HobbyGridAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hobby_layout);
        initView();
    }
//    private void initDrawble(){
//        mlist = new ArrayList<>();
//        hobbyTravel = new CheckBox(this);
//        hobbyTravel.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_travel,0,0);
//        hobbyTravel.setText("旅游");
//        hobbyTravel.setTextSize(18);
//        hobbyTravel.setButtonDrawable(null);
//        mlist.add(hobbyTravel);
//        hobbyBar = new CheckBox(this);
//        hobbyBar.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_bar,0,0);
//        hobbyBar.setText("酒吧");
//        hobbyBar.setTextSize(18);
//        hobbyBar.setButtonDrawable(null);
//        mlist.add(hobbyBar);
//        hobbyMusic=new CheckBox(this);
//        hobbyMusic.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_music,0,0);
//        hobbyMusic.setText("音乐");
//        hobbyMusic.setTextSize(18);
//        hobbyMusic.setButtonDrawable(null);
//        mlist.add(hobbyMusic);
//        hobbyThreater = new CheckBox(this);
//        hobbyThreater.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_threater,0,0);
//        hobbyThreater.setText("戏剧");
//        hobbyThreater.setTextSize(18);
//        mlist.add(hobbyThreater);
//        hobbyPicture = new CheckBox(this);
//        hobbyPicture.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_picture,0,0);
//        hobbyPicture.setText("展览");
//        hobbyPicture.setTextSize(18);
//        mlist.add(hobbyPicture);
//        hobbyFood = new CheckBox(this);
//        hobbyFood.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_eat,0,0);
//        hobbyFood.setText("美食");
//        hobbyFood.setTextSize(18);
//        mlist.add(hobbyFood);
//        hobbyShopping = new CheckBox(this);
//        hobbyShopping.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_shopping,0,0);
//        hobbyShopping.setText("酒吧");
//        hobbyShopping.setTextSize(18);
//        mlist.add(hobbyShopping);
//        hobbyMovie = new CheckBox(this);
//        hobbyMovie.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_movie,0,0);
//        hobbyMovie.setText("电影");
//        hobbyMovie.setTextSize(18);
//        mlist.add(hobbyMovie);
//        hobbyParty = new CheckBox(this);
//        hobbyParty.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_party,0,0);
//        hobbyParty.setText("聚会");
//        hobbyParty.setTextSize(18);
//        mlist.add(hobbyParty);
//        hobbySport = new CheckBox(this);
//        hobbySport.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_sport,0,0);
//        hobbySport.setText("运动");
//        hobbySport.setTextSize(18);
//        mlist.add(hobbySport);
//        hobbyPublic = new CheckBox(this);
//        hobbyPublic.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_party,0,0);
//        hobbyPublic.setText("聚会");
//        hobbyPublic.setTextSize(18);
//        mlist.add(hobbyPublic);
//        hobbyBusinness = new CheckBox(this);
//        hobbyBusinness.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.stateradiobutton,R.drawable.hobby_business,0,0);
//        hobbyBusinness.setText("酒吧");
//        hobbyBusinness.setTextSize(18);
//        mlist.add(hobbyBusinness);
//    }
    private void initView() {
        hobbyTravel = (CheckBox) findViewById(R.id.hobby_travel);
        hobbyBar = (CheckBox) findViewById(R.id.hobby_bar);
        hobbyMusic= (CheckBox) findViewById(R.id.hobby_music);
        hobbyThreater = (CheckBox) findViewById(R.id.hobby_threater);
        hobbyPicture = (CheckBox) findViewById(R.id.hobby_picture);
        hobbyMovie = (CheckBox) findViewById(R.id.hobby_movie);
        hobbyParty = (CheckBox) findViewById(R.id.hobby_party);
        hobbySport = (CheckBox) findViewById(R.id.hobby_sport);
        hobbyPublic = (CheckBox) findViewById(R.id.hobby_public);
        hobbyBusinness = (CheckBox) findViewById(R.id.hobby_business);
        hobbyFood = (CheckBox)findViewById(R.id.hobby_eat);
        hobbyShopping=(CheckBox)findViewById(R.id.hobby_shopping);
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.translate_in_two,R.anim.translate_out_two);
        finish();
    }

    public void onClickNext(View view) {
        StringBuilder sb = new StringBuilder(256);
        if(hobbyTravel.isChecked()){
            String s = hobbyTravel.getText().toString();
            sb.append(s);
        }else if(hobbyBar.isChecked()){
            sb.append(hobbyBar.getText().toString());
        }else if(hobbyMusic.isChecked()){
            sb.append(hobbyMusic.getText().toString());
        }else if(hobbyThreater.isChecked()){
            sb.append(hobbyThreater.getText().toString());
        }else if(hobbyPicture.isChecked()){
            sb.append(hobbyPublic.getText().toString());
        }else if(hobbyMovie.isChecked()){
            sb.append(hobbyMovie.getText().toString());
        }else if(hobbyParty.isChecked()){
            sb.append(hobbyParty.getText().toString());
        }else if(hobbySport.isChecked()){
            sb.append(hobbySport.getText().toString());
        }else if(hobbyPublic.isChecked()){
            sb.append(hobbyPublic.getText().toString());
        }else if(hobbyBusinness.isChecked()){
            sb.append(hobbyBusinness.getText().toString());
        }else if(hobbyFood.isChecked()){
            sb.append(hobbyFood.getText().toString());
        }else if(hobbyShopping.isChecked()){
            sb.append(hobbyShopping.getText().toString());
        }
        SharedPreferenceUtils.saveString(this,"hobby",sb.toString());
        if(hobbyTravel.isChecked()||hobbyBar.isChecked()|| hobbyMusic.isChecked()
                || hobbyThreater.isChecked()|| hobbyPicture.isChecked()|| hobbyMovie.isChecked()|| hobbyParty.isChecked()
                || hobbySport.isChecked()||hobbyPublic.isChecked()||hobbyBusinness.isChecked()
                ||hobbyFood.isChecked()||hobbyShopping.isChecked()){
            //如果有了选项，下次启动时，将不再启动splash和login以及此界面
            SharedPreferenceUtils.saveBoolean(this,"complete",true);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.translate_in,R.anim.translate_out);
            finish();
        }else {
            Toast.makeText(this, "还没有选择标签哦~~", Toast.LENGTH_SHORT).show();
        }

    }
}
