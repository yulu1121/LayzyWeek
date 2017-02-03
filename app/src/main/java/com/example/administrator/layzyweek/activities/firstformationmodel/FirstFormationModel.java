package com.example.administrator.layzyweek.activities.firstformationmodel;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public interface FirstFormationModel {
    //获取数据
    void getFirstFormationResult(String id);
    //发送数据
    interface FirstFormationSendResult{
        void sendFirstFormation(String result);
    }
}
