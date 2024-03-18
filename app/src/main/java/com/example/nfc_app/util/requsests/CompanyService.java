package com.example.nfc_app.util.requsests;

import com.example.nfc_app.util.dto.CompanyResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface CompanyService {
    @GET("/mobile/company/getById/{id}")
    Call<CompanyResponseDto> getCompanyById(@Header("Authorization") String token, @Path("id") int id);
}
