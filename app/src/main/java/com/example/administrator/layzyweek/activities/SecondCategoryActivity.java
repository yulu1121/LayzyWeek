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
import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.entries.Cities;
import com.example.administrator.layzyweek.entries.City;
import com.example.administrator.layzyweek.entries.FirstPage;
import com.example.administrator.layzyweek.selfadapter.MainFirstPageAdapter;
import com.example.administrator.layzyweek.utils.JsonLoader;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class SecondCategoryActivity extends BaseActivity implements SecondCategoryPresenter.SendCategory2View{
    private String category;
    private int currentPage = 1;
    private TextView categoryName;
    private PullToRefreshListView refreshListView;
    private MainFirstPageAdapter adapter;
    Bundle bundle = null;
    private int citiesId;
    private JsonLoader jsonLoader;
    private List<FirstPage.ResultBean> mlist;
    private SecondCategoryPresenter presenter = null;
    private List<Cities> citiesList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        bundle = intent.getBundleExtra("categoryBundle");
        category = bundle.getString("category");
        presenter = new SecondCategoryPresenterImpl(this);
//        String city = SharedPreferenceUtils.getString(this, "city");
       // presenter.getCategoryData(SharedPreferenceUtils.getFloat(this,"latitude"),SharedPreferenceUtils.getFloat(this,"longitude"),currentPage,cityId,category);
        setContentView(R.layout.second_category_layout);
        initView();
    }
    private List<Cities> initCity(){
        jsonLoader = new JsonLoader();
        final Gson gson = new Gson();
        final List<Cities> list = new ArrayList<>();
        jsonLoader.parseJson2String(Constants.URL_CITY, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                City city = gson.fromJson(json, City.class);
                List<City.ResultBean> result = city.getResult();
                for (int i = 0; i <result.size() ; i++) {
                    City.ResultBean resultBean = result.get(i);
                    for (int j = 0; j <resultBean.getCity_list().size() ; j++) {
                        Cities cities = new Cities();
                        String city_name = resultBean.getCity_list().get(j).getCity_name();
                        int city_id = resultBean.getCity_list().get(j).getCity_id();
                        cities.setCityName(city_name);
                        cities.setCityID(city_id);
                        list.add(cities);
                    }
                }
            }
        }
        );
        return list;
    }

    private void initView() {
//        cityId = getCityId("武汉");
        List<Cities> cityList = initCity();
        citiesList = new ArrayList<>();
        citiesList.addAll(cityList);
        for (int i = 0; i < citiesList.size(); i++) {
            Cities cities = citiesList.get(i);
            if(cities.getCityName().equals("武汉")){
                citiesId = cities.getCityID();
            }
        }
        Log.e("==",":"+citiesId);
        presenter.getCategoryData(30.575388756810078,114.30963859310197,currentPage,citiesId,category);
        categoryName = (TextView)findViewById(R.id.categoryName);
        categoryName.setText(bundle.getString("categoryName"));
        refreshListView = (PullToRefreshListView) findViewById(R.id.main_listView);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 1;
                mlist.clear();
                presenter.getCategoryData(30.575388756810078,114.30963859310197,currentPage,citiesId,category);
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
                presenter.getCategoryData(30.575388756810078,114.30963859310197,currentPage,citiesId,category);
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
        adapter = new MainFirstPageAdapter(this,mlist);
        refreshListView.setAdapter(adapter);
    }
}
