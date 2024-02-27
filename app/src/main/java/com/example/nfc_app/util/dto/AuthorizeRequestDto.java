package com.example.nfc_app.util.dto;

public class AuthorizeRequestDto {
    private String login;
    private String password;

    public AuthorizeRequestDto(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
