package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

public class AddLogRequestDto {
    @SerializedName("controlNum")
    private int controlNum;
    @SerializedName("controlNumStatus")
    private String controlNumStatus;
    @SerializedName("date")
    private String date;
    @SerializedName("tagNum")
    private String tagNum;
    @SerializedName("companyId")
    private int companyId;
    @SerializedName("facilityId")
    private int facilityId;
    @SerializedName("workId")
    private int workId;

    @SerializedName("uuid")
    private String uuid;


    public AddLogRequestDto(int controlNum, String controlNumStatus, String date, String tagNum, int companyId, int facilityId, int workId, String uuid) {
        this.controlNum = controlNum;
        this.controlNumStatus = controlNumStatus;
        this.date = date;
        this.tagNum = tagNum;
        this.companyId = companyId;
        this.facilityId = facilityId;
        this.workId = workId;
        this.uuid = uuid;
    }
}
