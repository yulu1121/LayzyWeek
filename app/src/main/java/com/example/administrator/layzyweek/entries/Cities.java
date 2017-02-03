package com.example.administrator.layzyweek.entries;

/**
 *
 * Created by Administrator on 2017/1/25.
 */

public class Cities {
    private int cityID;
    private String cityName;

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Cities{" +
                "cityID=" + cityID +
                ", cityName='" + cityName + '\'' +
                '}';
    }

}
