package com.example.administrator.layzyweek.activities.secondcategorypresenter;

import com.example.administrator.layzyweek.activities.secondcategorymodel.SecondCategoryModel;
import com.example.administrator.layzyweek.activities.secondcategorymodel.SecondCategoryModelImpl;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class SecondCategoryPresenterImpl implements SecondCategoryPresenter,SecondCategoryModel.SendCategoryResult {
    private SecondCategoryModel model;
    private SendCategory2View category2View;
    public SecondCategoryPresenterImpl(SendCategory2View category2View){
        this.category2View = category2View;
        model = new SecondCategoryModelImpl(this);
    }
    @Override
    public void getCategoryData(double longitude, double latitude, int page, int cityId, String categoryName) {
        model.getCategoryResult(longitude,latitude,page,cityId,categoryName);
    }

    @Override
    public void sendCategoryResult(String result) {
        category2View.sendCategory2View(result);
    }
}
