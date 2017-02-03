package com.example.administrator.layzyweek.fragments.firstfragmodel;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.utils.JsonLoader;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public class FirstFragModelImpl implements FirstFragModel {
    private SendMessage message;
    private JsonLoader jsonLoader;
    public FirstFragModelImpl(SendMessage message){
         this.message = message;
    }

    @Override
    public void getData(double latitude, double longitude, int page) {
        jsonLoader = new JsonLoader();
        String url = Constants.URL_MAIN_PAGE + "&lon=" + longitude + "&page=" + page + "&lat=" + latitude;
        jsonLoader.parseJson2String(url, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                message.sendData(json);
            }
        });
    }
}
