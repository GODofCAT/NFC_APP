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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DezinsecFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DezinsecFragment extends Fragment {
    TextView textViewTag;

    EditText editTextInsectCount;
    EditText editTextControllNum;
    Spinner spinnerWarehouses;
    String selection;
    int selectionId;
    Button buttonInsectNotFound;
    Button buttonInsectFound;
    ImageButton buttonBack;


    public DezinsecFragment() {
        // Required empty public constructor
    }


    public static DezinsecFragment newInstance(String param1, String param2) {
        DezinsecFragment fragment = new DezinsecFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dezinsec, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextControllNum = view.findViewById(R.id.editTextControllNumDezinsec);
        editTextInsectCount = view.findViewById(R.id.editTextInsectCount);

        editTextInsectCount.setBackgroundResource(R.drawable.round_shape);
        editTextControllNum.setBackgroundResource(R.drawable.round_shape);

        textViewTag = view.findViewById(R.id.textViewTag);

        spinnerWarehouses = view.findViewById(R.id.spinnerWarehousesDezinsec);
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

        buttonInsectNotFound = view.findViewById(R.id.buttonInsectNotFound);
        buttonInsectFound = view.findViewById(R.id.buttonInsectFound);

        buttonInsectFound.setOnClickListener(buttonInsectFoundOnClick);
        buttonInsectNotFound.setOnClickListener(buttonInsectNotFoundOnClick);

        buttonBack = view.findViewById(R.id.imageButtonBack);
        buttonBack.setOnClickListener(buttonBackOnClick);
    }

    View.OnClickListener buttonInsectFoundOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editTextInsectCount.getText().toString().isEmpty()){
                ((MainActivity)getActivity()).writeTag("`нд`", editTextControllNum.getText().toString(), selection);String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
                ((MainActivity)getActivity()).addNewLogToDb(Integer.valueOf(editTextControllNum.getText().toString()),"`нд`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);


            }
            else{
                ((MainActivity)getActivity()).writeTag("`"+editTextInsectCount.getText()+"`", editTextControllNum.getText().toString(), selection);
                String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
                ((MainActivity)getActivity()).addNewLogToDb(Integer.valueOf(editTextControllNum.getText().toString()),"`Погрыз`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

            }
        }
    };

    View.OnClickListener buttonInsectNotFoundOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ((MainActivity)getActivity()).writeTag("`+`", editTextControllNum.getText().toString(), selection);
            String tagNum = ((MainActivity)getActivity()).getCurrentTagId();
            ((MainActivity)getActivity()).addNewLogToDb(Integer.valueOf(editTextControllNum.getText().toString()),"`+`",tagNum,Integer.valueOf(LocalStorage.storage.get("companyId").toString()),selectionId, 1);

        }
    };

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