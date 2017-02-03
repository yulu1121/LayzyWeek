package com.example.administrator.layzyweek.utils.cityutils;

import com.example.administrator.layzyweek.entries.City;
import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class CityUtils implements CitiesPresenter.SendCity2View {
    private static Map<String,Integer> map = new LinkedHashMap<>();
    private CitiesPresenter citiesPresenter = new CitiesPresenterImpl(this);
    @Override
    public void sendCity2View(String data) {
        citiesPresenter.sendCityData();
        Gson gson = new Gson();
        City cities = gson.fromJson(data, City.class);
        for (int i = 0; i < cities.getResult().size(); i++) {
            String cityName = cities.getResult().get(i).getCity_list().get(i).getCity_name();
            int id = cities.getResult().get(i).getCity_list().get(i).getCity_id();
            map.put(cityName,id);
        }
    }
    public static int getCityId(String cityName){
            return map.get(cityName);
    }
}
