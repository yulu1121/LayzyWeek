package com.example.administrator.layzyweek.utils.cityutils;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.utils.JsonLoader;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class CityModelImpl implements CitiesModel {
    private JsonLoader jsonLoader;
    private SendCityResult cityResult;
    public CityModelImpl(SendCityResult cityResult){
        this.cityResult = cityResult;
    }
    @Override
    public void getCityResult() {
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.URL_CITY, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                cityResult.sendCityResult(json);
            }
        });
    }
}
