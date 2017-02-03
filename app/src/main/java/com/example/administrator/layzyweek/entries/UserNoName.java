package com.example.administrator.layzyweek.entries;

import java.util.List;

/**
 * 匿名用户
 * Created by Administrator on 2017/1/15.
 */

public class UserNoName {
    private String userName;

    @Override
    public String toString() {
        return "UserNoName{" +
                "userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userState='" + userState + '\'' +
                ", hobbyList=" + hobbyList +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserNoName(String userName, String userSex, String userState, List<String> hobbyList) {
        this.userName = userName;
        this.userSex = userSex;
        this.userState = userState;
        this.hobbyList = hobbyList;
    }

    private String userSex;
    private String userState;
    private List<String> hobbyList;

    public UserNoName() {
    }

    public String getUserSex() {
        return userSex;
    }

    public String getUserState() {
        return userState;
    }

    public List<String> getHobbyList() {
        return hobbyList;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public void setHobbyList(List<String> hobbyList) {
        this.hobbyList = hobbyList;
    }

}
