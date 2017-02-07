package com.example.administrator.layzyweek.activities.firstformationdragger;

import com.example.administrator.layzyweek.activities.FirstFormationActivity;

import dagger.Component;

/**
 *
 * Created by Administrator on 2017/2/4.
 */
@Component(modules = {FirstAppMoudel.class})
public interface FirstAppComponent {
    void inJect(FirstFormationActivity formationActivity);
}
