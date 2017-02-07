package com.example.administrator.layzyweek.activities.firstformationpresenter;

import com.example.administrator.layzyweek.activities.firstformationmodel.FirstFormationModel;
import com.example.administrator.layzyweek.entries.FirstPageDetail;

import javax.inject.Inject;

/**
 *
 * Created by Administrator on 2017/1/23.
 */

public class FirstFormationPresenterImpl implements FirstFormationPresenter,FirstFormationModel.FirstFormationSendResult {
    private FirstFormationModel model;
    @Inject
    FirstFormationSend2View send2View;
    public FirstFormationPresenterImpl(FirstFormationSend2View send2View,FirstFormationModel model){
        this.send2View = send2View;
        this.model = model;
    }
    @Override
    public void firstFormationGetData(String id) {
        model.getFirstFormationResult(id,this);
    }

    @Override
    public void sendFirstFormation(FirstPageDetail detail) {
        send2View.sendFormation2View(detail);
    }
}
