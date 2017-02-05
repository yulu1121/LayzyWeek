package com.example.administrator.layzyweek.activities.firstformationpresenter;

import com.example.administrator.layzyweek.entries.FirstPageDetail;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public interface FirstFormationPresenter {
    //向view层发送数据
    void firstFormationGetData(String id);
    interface FirstFormationSend2View{
        void sendFormation2View(FirstPageDetail detail);
    }
}
