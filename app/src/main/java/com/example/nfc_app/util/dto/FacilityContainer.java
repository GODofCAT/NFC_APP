package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

public class FacilityContainer {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    @SerializedName("isActive")
    private int isActive;

    public FacilityContainer(String name, int isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public String toString(){
        return getName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsActive() {
        return isActive;
    }
}
