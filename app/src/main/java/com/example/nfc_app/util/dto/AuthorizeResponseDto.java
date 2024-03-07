package com.example.nfc_app.util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizeResponseDto {
    @SerializedName("status")
    private int status;

    @SerializedName("token")
    private String token;

    @SerializedName("companyId")
    private int companyId;


    public AuthorizeResponseDto() {
    }

    public int getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public int getCompanyId() {
        return companyId;
    }
}
