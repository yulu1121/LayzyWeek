package com.example.administrator.layzyweek.fragments.first.fragment.presenter;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public interface FirstPresenter {
    void getResult(double latitude,double longitude,int page);
    interface SendResult{
        void Send2View(String result);
    }
}
