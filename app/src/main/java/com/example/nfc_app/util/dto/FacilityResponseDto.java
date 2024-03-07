package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacilityResponseDto {
    @SerializedName("status")
    private int status;
    @SerializedName("facilities")
    private List<FacilityContainer> facilities;

    public int getStatus() {
        return status;
    }

    public List<FacilityContainer> getFacilities() {
        return facilities;
    }
}
