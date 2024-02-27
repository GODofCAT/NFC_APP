package com.example.nfc_app.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nfc_app.R;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.AuthorizeRequestDto;
import com.example.nfc_app.util.dto.AuthorizeResponseDto;
import com.example.nfc_app.util.requsests.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorizeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorizeFragment extends Fragment {
    EditText editTextLogin;
    EditText editTextPassword;
    Button buttonAuthorize;

    public static AuthorizeFragment newInstance(String param1, String param2) {
        AuthorizeFragment fragment = new AuthorizeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_authorize, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextLogin = view.findViewById(R.id.editTextLogin);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonAuthorize = view.findViewById(R.id.buttonAuthorize);

        AuthorizeRequestDto requestDto = new AuthorizeRequestDto(editTextLogin.getText().toString(),editTextPassword.getText().toString());

        NetworkService.getInstance().getAuthorizeInterface().authorizeMobile(requestDto).enqueue(new Callback<AuthorizeResponseDto>() {
            @Override
            public void onResponse(Call<AuthorizeResponseDto> call, Response<AuthorizeResponseDto> response) {

                switch (response.body().getStatus()) {
                    case 200:
                        LocalStorage.storage.put("token", response.body().getContent().getToken());
                        LocalStorage.storage.put("companyId", response.body().getContent().getCompanyId());

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            LocalStorage.storage.replace("current fragment", LocalStorage.fragments.MainFragment);
                        }

                        getActivity().setTheme(R.style.Theme_MainFragment);
                        getActivity().getSupportFragmentManager().popBackStack();
                        break;
                    case 401:

                        break;
                }
            }

            @Override
            public void onFailure(Call<AuthorizeResponseDto> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}