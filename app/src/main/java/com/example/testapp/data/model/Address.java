package com.example.testapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;

public class Address implements Serializable {
    @SerializedName("street")
    @Expose
    @ColumnInfo(name = "street")
    private String street;

    @SerializedName("suite")
    @Expose
    @ColumnInfo(name = "suite")
    private String suite;

    @SerializedName("city")
    @Expose
    @ColumnInfo(name = "city")
    private String city;

    @SerializedName("zipcode")
    @Expose
    @ColumnInfo(name = "zipcode")
    private String zipcode;

    @SerializedName("geo")
    @Expose
    @Embedded
    private Geo geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }
}
