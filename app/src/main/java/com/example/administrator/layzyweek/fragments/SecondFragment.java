package com.example.administrator.layzyweek.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseFragment;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.entries.SecondKindEntry;
import com.example.administrator.layzyweek.fragments.second.fragment.mianpresenter.SecondFragPresenter;
import com.example.administrator.layzyweek.fragments.second.fragment.mianpresenter.SecondFragPresenterImpl;
import com.example.administrator.layzyweek.selfadapter.SecondPageKindAdapter;
import com.google.gson.Gson;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public class SecondFragment extends BaseFragment implements SecondFragPresenter.SecondSend2View{
    private GridView secondGrid;
    private SecondFragPresenter prsenter;
    private TextView city;
    private SecondPageKindAdapter adapter;
    private EditText secondSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_select_main,container,false);
        prsenter = new SecondFragPresenterImpl(this);
        prsenter.SecondPreGetResult();
        initView(view);
        return view;
    }

    private void initView(View view) {
        city = (TextView) view.findViewById(R.id.second_city_choice);
        secondSearch = (EditText) view.findViewById(R.id.second_city_search);
        secondGrid = (GridView) view.findViewById(R.id.second_grid);
    }

    @Override
    public void send2View(String result) {
        Gson gson = new Gson();
        SecondKindEntry fromJson = gson.fromJson(result, SecondKindEntry.class);
        adapter = new SecondPageKindAdapter(getContext(),fromJson.getResult());
        secondGrid.setAdapter(adapter);
    }
}
