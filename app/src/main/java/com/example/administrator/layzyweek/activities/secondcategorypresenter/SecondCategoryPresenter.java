package com.example.administrator.layzyweek.activities.secondcategorypresenter;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public interface SecondCategoryPresenter {
    void getCategoryData(double longitude,double latitude,int page,int cityId,String categoryName);
    interface SendCategory2View{
        void sendCategory2View(String data);
    }
}
