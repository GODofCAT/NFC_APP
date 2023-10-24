package com.example.nfc_app;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MainFragment extends Fragment {

    Button buttonDeratization;
    Button buttonDezinsect;

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonDeratization = view.findViewById(R.id.buttonDeratization);
        buttonDeratization.setOnClickListener(buttonDeratiazationOnClick);

        buttonDezinsect = view.findViewById(R.id.buttonDezinsect);
        buttonDezinsect.setOnClickListener(buttonDezinsectOnClick);
    }

    View.OnClickListener buttonDezinsectOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocalStorage.storage.replace("current fragment", LocalStorage.fragments.DezinsecFragment);
            }

            getActivity().setTheme(R.style.Theme_NFC_APP);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, (DezinsecFragment)LocalStorage.storage.get("dezinsecFragment"));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

    View.OnClickListener buttonDeratiazationOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocalStorage.storage.replace("current fragment", LocalStorage.fragments.NfcFragment);
            }

            getActivity().setTheme(R.style.Theme_NFC_APP);
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameLayout, (NfcFragment)LocalStorage.storage.get("nfcFragment"));
            transaction.addToBackStack(null);
            transaction.commit();
        }
    };

}