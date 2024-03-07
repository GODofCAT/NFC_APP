package com.example.nfc_app.util.dto;

import com.google.gson.annotations.SerializedName;

public class AuthorizeRequestDto {
    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;

    public AuthorizeRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
