package com.example.administrator.layzyweek.fragments.second.fragment.mianpresenter;

/**
 *
 * Created by Administrator on 2017/1/21.
 */

public interface SecondFragPresenter {
    //获取结果
    void SecondPreGetResult();
    //向View层发送数据
    interface SecondSend2View{
        void send2View(String result);
    }
}
