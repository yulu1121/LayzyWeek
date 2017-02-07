package com.example.administrator.layzyweek.activities.firstformationmodel;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.entries.FirstPageDetail;
import com.example.administrator.layzyweek.utils.JsonLoader;
import com.google.gson.Gson;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationModelImpl implements FirstFormationModel {
    private JsonLoader jsonLoader;
    @Override
    public void getFirstFormationResult(String id,final FirstFormationSendResult sendResult) {
        jsonLoader = new JsonLoader();
        String url = Constants.URL_FORMATION_DETAIL+"leo_id="+id+Constants.URL_FORMATION_DETAIL_TWO;
        jsonLoader.parseJson2String(url, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                Gson gson = new Gson();
                FirstPageDetail detail = gson.fromJson(json, FirstPageDetail.class);
                sendResult.sendFirstFormation(detail);
            }
        });
    }
}
