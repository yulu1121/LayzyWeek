package com.example.administrator.layzyweek.activities.firstformationmodel;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.utils.JsonLoader;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationModelImpl implements FirstFormationModel {
    private FirstFormationSendResult sendResult;
    private JsonLoader jsonLoader;
    public FirstFormationModelImpl(FirstFormationSendResult sendResult){
        this.sendResult = sendResult;
    }
    @Override
    public void getFirstFormationResult(String id) {
        jsonLoader = new JsonLoader();
        String url = Constants.URL_MAIN_FORMATION+"&leo_id="+id;
        jsonLoader.parseJson2String(url, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                sendResult.sendFirstFormation(json);
            }
        });
    }
}
