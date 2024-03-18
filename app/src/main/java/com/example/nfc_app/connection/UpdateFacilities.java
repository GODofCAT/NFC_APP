package com.example.nfc_app.connection;

import android.content.Context;
import android.widget.Toast;

import com.example.nfc_app.db.Facility;
import com.example.nfc_app.db.FacilityDb;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.FacilityContainer;
import com.example.nfc_app.util.dto.FacilityResponseDto;
import com.example.nfc_app.util.requsests.NetworkService;

import java.util.List;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFacilities extends TimerTask {
    private Context context;
    private FacilityDb facilityDb;

    public UpdateFacilities(Context context, FacilityDb facilityDb) {
        this.context = context;
        this.facilityDb = facilityDb;
    }


    @Override
    public void run() {
        if(NetworkUtils.isNetworkAvailable(context)){
            NetworkService.getInstance().getFacilityInterface().getFacilityesByCompanyId(LocalStorage.storage.get("token").toString(),(Integer)LocalStorage.storage.get("companyId")).enqueue(new Callback<FacilityResponseDto>() {
                @Override
                public void onResponse(Call<FacilityResponseDto> call, Response<FacilityResponseDto> response) {
                    switch (response.body().getStatus()){
                        case 200:
                            facilityDb.getFacilityDao().deleteFacilities();
                            List<FacilityContainer> facilityContainers= response.body().getFacilities();
                            for (FacilityContainer item:facilityContainers){
                                Facility facility = new Facility(item.getName(),item.getIsActive());
                                facilityDb.getFacilityDao().insert(facility);
                            }
                            LocalStorage.facilityContainers = facilityContainers;
                            break;
                        case 401:
                            break;
                    }
                }

                @Override
                public void onFailure(Call<FacilityResponseDto> call, Throwable t) {

                }
            });
        }
    }
}
