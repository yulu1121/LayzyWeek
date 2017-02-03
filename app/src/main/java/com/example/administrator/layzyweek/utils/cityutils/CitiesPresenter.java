package com.example.administrator.layzyweek.utils.cityutils;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public interface CitiesPresenter {
    void sendCityData();
    interface SendCity2View{
        void sendCity2View(String data);
    }
}
