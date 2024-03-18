package com.example.nfc_app.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.nfc_app.util.dto.FacilityContainer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity
public class Company {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    int id;

    String login;
    String password;

}
