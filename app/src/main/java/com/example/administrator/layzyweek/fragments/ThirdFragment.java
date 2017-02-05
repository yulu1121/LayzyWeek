package com.example.administrator.layzyweek.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.layzyweek.R;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public class ThirdFragment extends Fragment {
    private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }
    public static ThirdFragment newInstance(){
        ThirdFragment thirdFragment = new ThirdFragment();
        return thirdFragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_select_main,container,false);
        return view;
    }

}
