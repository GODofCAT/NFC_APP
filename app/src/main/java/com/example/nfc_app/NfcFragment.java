package com.example.nfc_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NfcFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NfcFragment extends Fragment {
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

    ImageButton buttonBack;




    public static NfcFragment newInstance() {
        NfcFragment fragment = new NfcFragment();

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
            getActivity().setTheme(R.style.Theme_MainFragment);
            getActivity().getSupportFragmentManager().popBackStack();
        }
    };

    View.OnClickListener button50OnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`Погрыз`", editTextControllNum.getText().toString(), selection);
        }
    };
    View.OnClickListener buttonGrOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`Гр`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonKontOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтПовр`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonMehOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`МехПовр`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonNedOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтНед`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`КонтОт`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonPrOtOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`ПрОт`", editTextControllNum.getText().toString(), selection);
        }
    };

    View.OnClickListener buttonPlusOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`+`", editTextControllNum.getText().toString(), selection);
        }
    };


}