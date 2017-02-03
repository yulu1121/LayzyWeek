package com.example.administrator.layzyweek.activities.secondcategorymodel;

import com.example.administrator.layzyweek.constans.Constants;
import com.example.administrator.layzyweek.utils.JsonLoader;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class SecondCategoryModelImpl implements SecondCategoryModel {
    private JsonLoader jsonLoader;
    private SendCategoryResult result;
    public SecondCategoryModelImpl(SendCategoryResult result){
        this.result = result;
    }
    @Override
    public void getCategoryResult(double longitude, double latitude, int page, int cityId,String categoryName) {
        jsonLoader = new JsonLoader();
        String url = Constants.URL_CATEGORY_FORMATION+"&lon="+longitude+"&page="+page+"&category="+categoryName+"&lat="+latitude+"&city_id="+cityId;
        jsonLoader.parseJson2String(url, new JsonLoader.JsonListener() {
            @Override
            public void JsonComplete(String json) {
                result.sendCategoryResult(json);
            }
        });
    }
}
