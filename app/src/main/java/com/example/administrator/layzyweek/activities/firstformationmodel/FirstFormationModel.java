package com.example.administrator.layzyweek.activities.firstformationmodel;

import com.example.administrator.layzyweek.entries.FirstPageDetail;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public interface FirstFormationModel {
    //获取数据
    void getFirstFormationResult(String id,FirstFormationSendResult sendResult);
    //发送数据
    interface FirstFormationSendResult{
        void sendFirstFormation(FirstPageDetail detail);
    }
}
