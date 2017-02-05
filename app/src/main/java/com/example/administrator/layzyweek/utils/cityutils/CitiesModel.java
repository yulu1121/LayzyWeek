package com.example.administrator.layzyweek.utils.cityutils;

import com.example.administrator.layzyweek.entries.City;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public interface CitiesModel {
    void getCityResult();
    interface SendCityResult{
        void sendCityResult(City city);
    }
}
