package com.example.testapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.room.ColumnInfo;

public class Company implements Serializable {
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "company_name")
    private String name;

    @SerializedName("catchPhrase")
    @Expose
    @ColumnInfo(name = "catchphrase")
    private String catchPhrase;

    @SerializedName("bs")
    @Expose
    @ColumnInfo(name = "bs")
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
