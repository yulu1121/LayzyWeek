package com.example.administrator.layzyweek.utils.cityutils;

import com.example.administrator.layzyweek.entries.City;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class CitiesPresenterImpl implements CitiesPresenter,CitiesModel.SendCityResult{
    private SendCity2View sendCity2View;
    private CitiesModel model;
    public CitiesPresenterImpl(SendCity2View sendCity2View){
        this.sendCity2View = sendCity2View;
        model = new CityModelImpl(this);
    }
    @Override
    public void sendCityData() {
        model.getCityResult();
    }

    @Override
    public void sendCityResult(City city) {
        sendCity2View.sendCity2View(city);
    }
}
