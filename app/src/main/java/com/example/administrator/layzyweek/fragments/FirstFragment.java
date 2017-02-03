package com.example.administrator.layzyweek.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.layzyweek.BaseFragment;
import com.example.administrator.layzyweek.R;
import com.example.administrator.layzyweek.entries.FirstPage;
import com.example.administrator.layzyweek.fragments.first.fragment.presenter.FirstPresenter;
import com.example.administrator.layzyweek.fragments.first.fragment.presenter.FirstPresenterImpl;
import com.example.administrator.layzyweek.selfadapter.MainFirstPageAdapter;
import com.example.administrator.layzyweek.utils.SharedPreferenceUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2017/1/17.
 */

public class FirstFragment extends BaseFragment implements FirstPresenter.SendResult{
    private FirstPresenter firstPresenter;
    private int currentPage = 0;
    private MainFirstPageAdapter adapter;
    private PullToRefreshListView listView;
    private List<FirstPage.ResultBean> mlist;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.first_select_main,container,false);
        initView(view);
        firstPresenter = new FirstPresenterImpl(this);
//        firstPresenter.getResult(SharedPreferenceUtils.getFloat(getContext(),"latitude"),SharedPreferenceUtils.getFloat(getContext(),"longitude"),currentPage);
        firstPresenter.getResult(30.575388756810078,114.30963859310197,currentPage);
        return view;
    }

    private void initView(View view) {
        listView= (PullToRefreshListView) view.findViewById(R.id.main_listView);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        ListView listView1 = listView.getRefreshableView();
        TextView textView = new TextView(getContext());
        textView.setText("精选活动");
        textView.setTextSize(18);
        textView.setBackgroundColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        listView1.addHeaderView(textView);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage = 0;
                mlist.clear();
                //firstPresenter.getResult(SharedPreferenceUtils.getFloat(getContext(),"latitude"),SharedPreferenceUtils.getFloat(getContext(),"longitude"),currentPage);
                firstPresenter.getResult(30.575388756810078,114.30963859310197,currentPage);
                firstPresenter  = new FirstPresenterImpl(new FirstPresenter.SendResult() {
                    @Override
                    public void Send2View(String result) {
                        Gson gson = new Gson();
                        String state = SharedPreferenceUtils.getString(getContext(), "state");
                        FirstPage firstPage = gson.fromJson(result, FirstPage.class);
                        List<FirstPage.ResultBean> result1 = firstPage.getResult();
                        for (int i = 0; i <result1.size(); i++) {
                            if(!result1.get(i).getTags().contains(state)) {
                                result1.remove(i);
                            }
                        }
                        mlist.addAll(result1);
                        adapter.notifyDataSetChanged();
                    }
                });
                refreshView.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                currentPage++;
//                firstPresenter.getResult(SharedPreferenceUtils.getFloat(getContext(),"latitude"),SharedPreferenceUtils.getFloat(getContext(),"longitude"),currentPage);
                firstPresenter.getResult(30.575388756810078,114.30963859310197,currentPage);
                firstPresenter = new FirstPresenterImpl(new FirstPresenter.SendResult() {
                    @Override
                    public void Send2View(String result) {
                        Gson gson = new Gson();
                        String state = SharedPreferenceUtils.getString(getContext(), "state");
                        FirstPage firstPage = gson.fromJson(result, FirstPage.class);
                        List<FirstPage.ResultBean> result1 = firstPage.getResult();
                        for (int i = 0; i <result1.size(); i++) {
                            if(!result1.get(i).getTags().contains(state)) {
                                result1.remove(i);
                            }
                        }
                        mlist.addAll(result1);
                        adapter.notifyDataSetChanged();
                    }
                });
                refreshView.onRefreshComplete();
            }
        });
    }

    @Override
    public void Send2View(String result) {
        mlist = new ArrayList<>();
        Gson gson = new Gson();
        String state = SharedPreferenceUtils.getString(getContext(), "state");
        FirstPage firstPage = gson.fromJson(result, FirstPage.class);
        List<FirstPage.ResultBean> result1 = firstPage.getResult();
        for (int i = 0; i <result1.size(); i++) {
           if(!result1.get(i).getTags().contains(state)) {
               result1.remove(i);
           }
        }
        mlist.addAll(result1);
        adapter = new MainFirstPageAdapter(getContext(),mlist);
        listView.setAdapter(adapter);
    }
}
