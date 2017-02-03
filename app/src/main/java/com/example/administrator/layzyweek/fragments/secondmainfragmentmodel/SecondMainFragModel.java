package com.example.administrator.layzyweek.fragments.secondmainfragmentmodel;

/**
 *
 * Created by Administrator on 2017/1/21.
 */

public interface SecondMainFragModel {
    //有获取数据的方法
   void getData();
    //有向上层presenter发送数据的信鸽
    interface SecondSendData{
        void sendData(String data);
    }
}
