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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nfc_app.R;
import com.example.nfc_app.util.LocalStorage;
import com.example.nfc_app.util.dto.FacilityContainer;

public class DeratizationFragment extends Fragment {
    TextView textViewTag;
    Button buttonPogr;
    Button buttonPlus;
    Button buttonGr;
    Button buttonPrOt;
    Button buttonKontZam;
    Button buttonMeh;
    Button buttonNed;
    Button buttonOt;

    Button button25per;
    Button button50per;
    Button button75per;
    Button buttonPerCancel;

    EditText editTextControllNum;

    Spinner spinnerWarehouses;

    String selection;
    int selectionId;
    String controlNum;

    ImageButton buttonBack;

    LinearLayout coreButtonsLayout;
    LinearLayout perButtonsLayout;


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

        buttonPogr = view.findViewById(R.id.button50);
        buttonGr = view.findViewById(R.id.buttonGr);
        buttonKontZam = view.findViewById(R.id.buttonKontZam);
        buttonMeh = view.findViewById(R.id.buttonMeh);
        buttonNed = view.findViewById(R.id.buttonNed);
        buttonOt = view.findViewById(R.id.buttonOt);
        buttonPrOt = view.findViewById(R.id.buttonPrOt);
        buttonPlus = view.findViewById(R.id.buttonPlus);
        button25per = view.findViewById(R.id.button25per);
        button50per = view.findViewById(R.id.button50per);
        button75per = view.findViewById(R.id.button75per);
        buttonPerCancel = view.findViewById(R.id.buttonPerCancel);

        coreButtonsLayout = view.findViewById(R.id.coreButtonsLayout);
        perButtonsLayout  = view.findViewById(R.id.perButtonsLayout);

        buttonPogr.setOnClickListener(buttonPogrOnClick);
        buttonGr.setOnClickListener(buttonGrOnClick);
        buttonKontZam.setOnClickListener(buttonKontZamOnClick);
        buttonMeh.setOnClickListener(buttonMehOnClick);
        buttonNed.setOnClickListener(buttonNedOnClick);
        buttonOt.setOnClickListener(buttonOtOnClick);
        buttonPrOt.setOnClickListener(buttonPrOtOnClick);
        buttonPlus.setOnClickListener(buttonPlusOnClick);

        button25per.setOnClickListener(button25OnClick);
        button50per.setOnClickListener(button50OnClick);
        button75per.setOnClickListener(button75OnClick);
        buttonPerCancel.setOnClickListener(buttonPerCancelOnClick);

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

    View.OnClickListener buttonPogrOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isPercentageButtonVisible(true);
        }
    };

    View.OnClickListener button25OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");
            isPercentageButtonVisible(false);

            ((MainActivity)getActivity()).writeTag("`Погрыз-25%`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`Погрыз-25%`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener button50OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");
            isPercentageButtonVisible(false);


            ((MainActivity)getActivity()).writeTag("`Погрыз-50%`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`Погрыз-50%`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener button75OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");
            isPercentageButtonVisible(false);


            ((MainActivity)getActivity()).writeTag("`Погрыз-75%`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`Погрыз-75%`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonPerCancelOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            isPercentageButtonVisible(false);
        }
    };

    View.OnClickListener buttonGrOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`Гр`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`Гр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonKontZamOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`КонтЗам`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`КонтПовр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonMehOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`МехПовр`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`МехПовр`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonNedOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`КонтНед`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`КонтНед`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`КонтОт`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`КонтОт`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonPrOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`ПрОт`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`ПрОт`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    View.OnClickListener buttonPlusOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            controlNum = editTextControllNum.getText().toString();
            editTextControllNum.setText("");

            ((MainActivity)getActivity()).writeTag("`+`", controlNum, selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(controlNum,"`+`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);
        }
    };

    private void isPercentageButtonVisible(boolean isVisible){
        if (isVisible){
            coreButtonsLayout.setVisibility(View.GONE);
            perButtonsLayout.setVisibility(View.VISIBLE);
        }else {
            coreButtonsLayout.setVisibility(View.VISIBLE);
            perButtonsLayout.setVisibility(View.GONE);
        }
    }


}