package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LogResponseDto {
    @SerializedName("status")
    private int status;

    @SerializedName("uuids")
    private List<String> uuids;

    public int getStatus() {
        return status;
    }

    public List<String> getUuids() {
        return uuids;
    }
}
