package com.example.nfc_app.util.requsests;

import com.example.nfc_app.util.dto.FacilityResponseDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface FacilityInterface {
    @GET("mobile/facility/get-all-by-company-id/{id}")
    Call<FacilityResponseDto> getFacilityesByCompanyId(@Header("Authorization") String token, @Path("id") int companyId);
}
