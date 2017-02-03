package com.example.administrator.layzyweek.fragments.secondmainfragmentmodel;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.utils.JsonLoader;

/**
 * 是model层的实现类
 * Created by Administrator on 2017/1/21.
 */

public class SecondMainFragModelImpl implements SecondMainFragModel {
    private SecondSendData sendData;
    private JsonLoader jsonLoader;
    public SecondMainFragModelImpl(SecondSendData sendData){
        this.sendData = sendData;
    }
    @Override
    public void getData(){
        jsonLoader = new JsonLoader();
        jsonLoader.parseJson2String(Constants.URL_SECOND_PAGE, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                sendData.sendData(json);
            }
        });
    }
}
