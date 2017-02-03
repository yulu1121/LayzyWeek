package com.example.administrator.layzyweek.activities.secondcategorymodel;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public interface SecondCategoryModel{
    void getCategoryResult(double longitude,double latitude,int page,int cityId,String categoryName);
    interface SendCategoryResult{
        void sendCategoryResult(String result);
    }
}
