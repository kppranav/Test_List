package com.example.testapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;

public class Geo implements Serializable {
    @SerializedName("lat")
    @Expose
    @ColumnInfo(name = "lat")
    private String lat;

    @SerializedName("lng")
    @Expose
    @ColumnInfo(name = "lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
