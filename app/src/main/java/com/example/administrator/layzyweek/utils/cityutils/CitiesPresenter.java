package com.example.administrator.layzyweek.utils.cityutils;

import com.example.administrator.layzyweek.entries.City;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public interface CitiesPresenter {
    void sendCityData();
    interface SendCity2View{
        void sendCity2View(City city);
    }
}
