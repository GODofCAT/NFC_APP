package com.example.nfc_app.fragments;

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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nfc_app.R;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.FacilityContainer;


public class FerMonFragment extends Fragment {
    TextView textViewTag;

    Spinner spinnerWarehouses;
    String selection;
    int selectionId;

    EditText editTextControllNumFerMon;
    EditText editTextDolgonosCount;
    EditText editTextControllHruschCount;

    Button buttonHrusch;
    Button buttonDolgonos;
    ImageButton buttonBack;

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

        editTextControllNumFerMon = view.findViewById(R.id.editTextControllNumFerMon);
        editTextDolgonosCount = view.findViewById(R.id.editTextDolgonosCount);
        editTextControllHruschCount = view.findViewById(R.id.editTextControllHruschCount);

        editTextControllNumFerMon.setBackgroundResource(R.drawable.round_shape);
        editTextDolgonosCount.setBackgroundResource(R.drawable.round_shape);
        editTextControllHruschCount.setBackgroundResource(R.drawable.round_shape);

        spinnerWarehouses = view.findViewById(R.id.spinnerWarehouses);
        ArrayAdapter<FacilityContainer> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, LocalStorage.facilityContainers);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerWarehouses.setAdapter(adapter);
        spinnerWarehouses.setBackgroundResource(R.drawable.round_shape);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FacilityContainer  item = (FacilityContainer) parent.getItemAtPosition(position);
                selection = item.getName();
                selectionId = item.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinnerWarehouses.setOnItemSelectedListener(itemSelectedListener);

        buttonDolgonos = view.findViewById(R.id.buttonDolgonos);
        buttonHrusch = view.findViewById(R.id.buttonHrusch);

        buttonDolgonos.setOnClickListener(buttonDolgonosOnClick);
        buttonHrusch.setOnClickListener(buttonHruschOnClick);

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

    View.OnClickListener buttonDolgonosOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String msg;

            if (editTextDolgonosCount.getText().toString().isEmpty()){
                msg = "`АД-0`";
            }
            else{
                msg = "`АД-"+editTextDolgonosCount.getText().toString()+"`";
            }
            ((MainActivity)getActivity()).writeTag(msg, editTextControllNumFerMon.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonHruschOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextControllHruschCount.getText().toString().isEmpty()){
                ((MainActivity)getActivity()).writeTag("`МХ-0`", editTextControllHruschCount.getText().toString(), selection);
                String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
                ((MainActivity)getActivity()).addNewLogToDb(Integer.valueOf(editTextControllHruschCount.getText().toString()),"`МХ-0`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

            }
            else{
                ((MainActivity)getActivity()).writeTag("`МХ-"+editTextControllHruschCount.getText()+"`", editTextControllNumFerMon.getText().toString(), selection);
                String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
                ((MainActivity)getActivity()).addNewLogToDb(Integer.valueOf(editTextControllHruschCount.getText().toString()),"`МХ-"+editTextControllHruschCount.getText()+"`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

            }
        }
    };
}