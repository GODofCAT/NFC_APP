package com.example.nfc_app.util.requsests;

import com.example.nfc_app.util.dto.AddLogRequestDto;
import com.example.nfc_app.util.dto.LogResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LogInterface {
    @POST("mobile/logs/add-new")
    Call<LogResponseDto> addNewLog(@Header("Authorization") String token, @Body List<AddLogRequestDto> addLogRequestDto);
}
