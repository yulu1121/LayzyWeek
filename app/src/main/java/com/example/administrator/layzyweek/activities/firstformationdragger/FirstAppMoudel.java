package com.example.administrator.layzyweek.activities.firstformationdragger;

import com.example.administrator.layzyweek.activities.firstformationmodel.FirstFormationModel;
import com.example.administrator.layzyweek.activities.firstformationmodel.FirstFormationModelImpl;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenter;
import com.example.administrator.layzyweek.activities.firstformationpresenter.FirstFormationPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 *
 * Created by Administrator on 2017/2/4.
 */
@Module
public class FirstAppMoudel {
    private FirstFormationPresenter.FirstFormationSend2View send2View;

    public FirstAppMoudel(FirstFormationPresenter.FirstFormationSend2View send2View) {
        this.send2View = send2View;
    }
    @Provides
    public FirstFormationModel getModel(){
        return new FirstFormationModelImpl();
    }
    @Provides
    public FirstFormationPresenter getPresenter(FirstFormationModel model){
        return new FirstFormationPresenterImpl(send2View,model);
    }
}
