package com.example.administrator.layzyweek.fragments.firstfragmodel;

/**
 *
 * Created by Administrator on 2017/1/18.
 */

public interface FirstFragModel {
    //具有接受数据的功能
    void getData(double latitude,double longitude,int page);
    //向presenter发送数据的信鸽
     interface SendMessage{
        void sendData(String data);
    }
}
