package com.example.administrator.layzyweek.activities.firstformationpresenter;

import com.example.administrator.layzyweek.activities.firstformationmodel.FirstFormationModel;
import com.example.administrator.layzyweek.activities.firstformationmodel.FirstFormationModelImpl;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationPresenterImpl implements FirstFormationPresenter,FirstFormationModel.FirstFormationSendResult {
    private FirstFormationModel model;
    private FirstFormationSend2View send2View;
    public FirstFormationPresenterImpl(FirstFormationSend2View send2View){
        this.send2View = send2View;
        model = new FirstFormationModelImpl(this);
    }
    @Override
    public void firstFormationGetData(String id) {
        model.getFirstFormationResult(id);
    }

    @Override
    public void sendFirstFormation(String result) {
        send2View.sendFormation2View(result);
    }
}
