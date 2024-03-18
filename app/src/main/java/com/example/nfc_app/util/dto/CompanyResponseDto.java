package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

public class CompanyResponseDto {
    @SerializedName("status")
    private int status;

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

    @SerializedName("isActive")
    private int isActive;
}
