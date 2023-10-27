package com.example.nfc_app;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class FerMonFragment extends Fragment {
    TextView textViewTag;

    Spinner spinnerWarehouses;
    String selection;

    EditText editTextControllNumFerMon;
    EditText editTextDolgonosCount;
    EditText editTextControllHruschCount;

    Button buttonHrusch;
    Button buttonDolgonos;
    Button buttonBack;

    public FerMonFragment() {
        // Required empty public constructor
    }

    public static FerMonFragment newInstance(String param1, String param2) {
        FerMonFragment fragment = new FerMonFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fermon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        textViewTag = view.findViewById(R.id.textViewTag);

        spinnerWarehouses = view.findViewById(R.id.spinnerWarehouses);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, LocalStorage.warehouses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWarehouses.setAdapter(adapter);
        spinnerWarehouses.setBackgroundResource(R.drawable.round_shape);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = (String)parent.getItemAtPosition(position);
                selection = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerWarehouses.setOnItemSelectedListener(itemSelectedListener);

        buttonDolgonos = view.findViewById(R.id.buttonDolgonos);
        buttonHrusch = view.findViewById(R.id.buttonHrusch);

        buttonBack = view.findViewById(R.id.imageButtonBack);
        buttonBack.setOnClickListener(buttonBackOnClick);
    }

    View.OnClickListener buttonBackOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                LocalStorage.storage.replace("current fragment", LocalStorage.fragments.MainFragment);
            }

            getActivity().setTheme(R.style.Theme_MainFragment);
            getActivity().getSupportFragmentManager().popBackStack();
        }
    };
}