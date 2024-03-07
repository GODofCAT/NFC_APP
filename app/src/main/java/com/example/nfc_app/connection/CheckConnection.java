package com.example.nfc_app.connection;

import android.content.Context;

import com.example.nfc_app.db.Log;
import com.example.nfc_app.db.LogDb;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.AddLogRequestDto;
import com.example.nfc_app.util.dto.LogResponseDto;
import com.example.nfc_app.util.requsests.NetworkService;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckConnection extends TimerTask {
    private Context context;
    private LogDb db;
    public CheckConnection(Context context, LogDb db){
        this.context = context;
        this.db = db;
    }
    public void run() {
        if(NetworkUtils.isNetworkAvailable(context)){
           List<Log> logs =  db.getLogDao().getLogs();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<AddLogRequestDto> request = logs.stream().map(log -> new AddLogRequestDto(log.getControl_num(), log.getControl_num_status(), log.getDate(), log.getTag_num(), log.getCompany_id(), log.getFacility_id(), log.getWork_id(), log.getUuid())).collect(Collectors.toList());

                NetworkService.getInstance().getLogInterface().addNewLog(LocalStorage.storage.get("token").toString(), request).enqueue(new Callback<LogResponseDto>() {
                    @Override
                    public void onResponse(Call<LogResponseDto> call, Response<LogResponseDto> response) {
                        switch (response.body().getStatus()){
                            case 200:
                                for(String uuid:response.body().getUuids()){
                                    if (db.getLogDao().findByUUID(uuid) != null){
                                        db.getLogDao().deleteByUUID(uuid);
                                    }
                                }
                                break;
                            case 401:
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<LogResponseDto> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }

        }else {

        }
    }
}
