package com.example.nfc_app.util.requsests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
    private static final String BASE_URL = "http://90.156.226.5:28040/";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public FacilityInterface getFacilityInterface(){
        return  mRetrofit.create(FacilityInterface.class);
    }

    public AuthorizeInterface getAuthorizeInterface() {
        return mRetrofit.create(AuthorizeInterface.class);
    }

    public LogInterface getLogInterface(){
        return  mRetrofit.create(LogInterface.class);
    }
}
