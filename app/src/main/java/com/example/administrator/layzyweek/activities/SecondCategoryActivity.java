package com.example.administrator.layzyweek.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseActivity;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.activities.secondcategorypresenter.SecondCategoryPresenter;
import com.example.administrator.layzyweek.activities.secondcategorypresenter.SecondCategoryPresenterImpl;
import com.example.administrator.layzyweek.entries.City;
import com.example.administrator.layzyweek.entries.FirstPage;
import com.example.administrator.layzyweek.selfadapter.SecondCategoryAdapter;
import com.example.administrator.layzyweek.utils.SharedPreferenceUtils;
import com.example.administrator.layzyweek.utils.cityutils.CitiesPresenter;
import com.example.administrator.layzyweek.utils.cityutils.CitiesPresenterImpl;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class SecondCategoryActivity extends BaseActivity implements SecondCategoryPresenter.SendCategory2View,CitiesPresenterImpl.SendCity2View{
    private String category;
    private int currentPage = 1;
    private TextView categoryName;
    private PullToRefreshListView refreshListView;
    private SecondCategoryAdapter adapter;
    Bundle bundle = null;
    private int citiesId;
    private String cityName;
    private CitiesPresenter citiesPresenter;
    private List<FirstPage.ResultBean> mlist;
    private SecondCategoryPresenter presenter = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("categoryBundle");
        category = bundle.getString("category");
        presenter = new SecondCategoryPresenterImpl(this);
        citiesPresenter = new CitiesPresenterImpl(this);
        setContentView(R.layout.second_category_layout);
        citiesPresenter.sendCityData();
    }
    @Override
    public void sendCity2View(City city) {
        cityName = SharedPreferenceUtils.getString(this,"city");
        String substring = cityName.substring(0, cityName.length() - 1);
        Log.e("xx",substring);
        List<City.ResultBean> result = city.getResult();
        for (int i = 0; i < result.size(); i++) {
            List<City.ResultBean.CityListBean> city_list = result.get(i).getCity_list();
            for (int j = 0; j <city_list.size() ; j++) {
                if(city_list.get(j).getCity_name().equals(substring)){
                    citiesId = city_list.get(j).getCity_id();
                    initView();
                }
            }
        }
    }

    private void initView() {
//        presenter.getCategoryData(30.575388756810078,114.30963859310197,currentPage,citiesId,category);
        presenter.getCategoryData(SharedPreferenceUtils.getFloat(this,"latitude"),SharedPreferenceUtils.getFloat(this,"longitude"),currentPage,citiesId,category);
        categoryName = (TextView)findViewById(R.id.categoryName);
        categoryName.setText(bundle.getString("categoryName"));
        refreshListView = (PullToRefreshListView) findViewById(R.id.main_listView);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;
                mlist.clear();
              //  presenter.getCategoryData(30.575388756810078,114.30963859310197,currentPage,citiesId,category);
                presenter.getCategoryData(SharedPreferenceUtils.getFloat(SecondCategoryActivity.this,"latitude"),SharedPreferenceUtils.getFloat(SecondCategoryActivity.this,"longitude"),currentPage,citiesId,category);
                presenter = new SecondCategoryPresenterImpl(new SecondCategoryPresenter.SendCategory2View() {
                    @Override
                    public void sendCategory2View(String data) {
                        Gson gson = new Gson();
                        FirstPage firstPage = gson.fromJson(data, FirstPage.class);
                        mlist.addAll(firstPage.getResult());
                        adapter.notifyDataSetChanged();
                    }
                });
                refreshView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
                presenter.getCategoryData(SharedPreferenceUtils.getFloat(SecondCategoryActivity.this,"latitude"),SharedPreferenceUtils.getFloat(SecondCategoryActivity.this,"longitude"),currentPage,citiesId,category);
                presenter = new SecondCategoryPresenterImpl(new SecondCategoryPresenter.SendCategory2View() {
                    @Override
                    public void sendCategory2View(String data) {
                        Gson gson = new Gson();
                        FirstPage firstPage = gson.fromJson(data, FirstPage.class);
                        mlist.addAll(firstPage.getResult());
                        adapter.notifyDataSetChanged();
                    }
                });
                refreshView.onRefreshComplete();
             }
        });

    }

    @Override
    public void sendCategory2View(String data) {
        Gson gson = new Gson();
        mlist = new ArrayList<>();
        FirstPage firstPage = gson.fromJson(data,FirstPage.class);
        mlist.addAll(firstPage.getResult());
        adapter = new SecondCategoryAdapter(this,mlist);
        refreshListView.setAdapter(adapter);
    }

}
