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

public class DeratizationFragment extends Fragment {
    TextView textViewTag;
    Button buttonPlus;
    Button button50;
    Button buttonGr;
    Button buttonPrOt;
    Button buttonKont;
    Button buttonMeh;
    Button buttonNed;
    Button buttonOt;

    EditText editTextControllNum;

    Spinner spinnerWarehouses;

    String selection;
    int selectionId;

    ImageButton buttonBack;




    public static DeratizationFragment newInstance() {
        DeratizationFragment fragment = new DeratizationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nfc, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewTag = view.findViewById(R.id.textViewTag);

        editTextControllNum = view.findViewById(R.id.editTextControllNum);
        editTextControllNum.setBackgroundResource(R.drawable.round_shape);



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

        button50 = view.findViewById(R.id.button50);
        buttonGr = view.findViewById(R.id.buttonGr);
        buttonKont = view.findViewById(R.id.buttonKont);
        buttonMeh = view.findViewById(R.id.buttonMeh);
        buttonNed = view.findViewById(R.id.buttonNed);
        buttonOt = view.findViewById(R.id.buttonOt);
        buttonPrOt = view.findViewById(R.id.buttonPrOt);
        buttonPlus = view.findViewById(R.id.buttonPlus);

        button50.setOnClickListener(button50OnClick);
        buttonGr.setOnClickListener(buttonGrOnClick);
        buttonKont.setOnClickListener(buttonKontOnClick);
        buttonMeh.setOnClickListener(buttonMehOnClick);
        buttonNed.setOnClickListener(buttonNedOnClick);
        buttonOt.setOnClickListener(buttonOtOnClick);
        buttonPrOt.setOnClickListener(buttonPrOtOnClick);
        buttonPlus.setOnClickListener(buttonPlusOnClick);

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

    View.OnClickListener button50OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ((MainActivity)getActivity()).writeTag("`Погрыз`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`Погрыз`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };
    View.OnClickListener buttonGrOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`Гр`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`Гр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonKontOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтПовр`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`КонтПовр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonMehOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`МехПовр`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`МехПовр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonNedOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтНед`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`КонтНед`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтОт`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`КонтОт`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonPrOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`ПрОт`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`ПрОт`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

    View.OnClickListener buttonPlusOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`+`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(editTextControllNum.getText().toString(),"`+`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };


}