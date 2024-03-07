package com.example.nfc_app.util.requsests;

import com.example.nfc_app.util.dto.AuthorizeRequestDto;
import com.example.nfc_app.util.dto.AuthorizeResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthorizeInterface {
    @POST("mobile/authorize/web-mobile")
    Call<AuthorizeResponseDto> authorizeMobile(@Body AuthorizeRequestDto authorizeRequestDto);
}
