package com.example.android.getusers;

import android.content.Context;

import com.example.android.getusers.R;

/**
 * Created by jyoti on 8/27/15.
 */
public class CustomLocation {

    private String name;
    private String latitude;
    private String longitude;

    public CustomLocation(String name, String latitude, String longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static CustomLocation getLocation(String name,Context context){
        CustomLocation ret;
        if(name.equalsIgnoreCase(context.getString(R.string.san_francisco))){
            ret = new CustomLocation(name,"37.730631","-122.435203");
        }else{
            ret = new CustomLocation(name,"51.895422","-8.458930");
        }
        return ret;
    }
}
