package com.example.administrator.layzyweek.fragments.first.fragment.presenter;

import com.example.administrator.layzyweek.fragments.firstfragmodel.FirstFragModel;
import com.example.administrator.layzyweek.fragments.firstfragmodel.FirstFragModelImpl;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public class FirstPresenterImpl implements FirstPresenter,FirstFragModel.SendMessage{
    private FirstFragModel model;
    private SendResult sendResult;
    public FirstPresenterImpl(SendResult sendResult){
        this.sendResult = sendResult;
        model = new FirstFragModelImpl(this);
    }


    @Override
    public void sendData(String data) {
        sendResult.Send2View(data);
    }

    @Override
    public void getResult(double latitude, double longitude, int page) {
        model.getData(latitude,longitude,page);
    }
}
