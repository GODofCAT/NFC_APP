package com.example.nfc_app.util.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorizeResponseDto {
    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("status")
    @Expose
    private String msgType;

    @SerializedName("content")
    @Expose
    private AuthorizeResponseContentDto content;

    public int getStatus() {
        return status;
    }

    public String getMsgType() {
        return msgType;
    }

    public AuthorizeResponseContentDto getContent() {
        return content;
    }
}
