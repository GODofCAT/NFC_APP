package com.example.nfc_app.util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizeResponseContentDto {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("companyId")
    @Expose
    private int companyId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
