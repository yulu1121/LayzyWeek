package com.example.administrator.layzyweek.fragments.second.fragment.mianpresenter;

import com.example.administrator.layzyweek.fragments.secondmainfragmentmodel.SecondMainFragModel;
import com.example.administrator.layzyweek.fragments.secondmainfragmentmodel.SecondMainFragModelImpl;

/**
 *
 * Created by Administrator on 2017/1/21.
 */

public class SecondFragPresenterImpl implements SecondFragPresenter,SecondMainFragModel.SecondSendData {
    private SecondMainFragModel model;
    private SecondSend2View secondSend2View;
    public SecondFragPresenterImpl(SecondSend2View secondSend2View){
        this.secondSend2View = secondSend2View;
        model = new SecondMainFragModelImpl(this);
    }
    @Override
    public void SecondPreGetResult() {
        model.getData();
    }

    @Override
    public void sendData(String data) {
        secondSend2View.send2View(data);
    }
}
