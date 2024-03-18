package com.example.nfc_app.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Facility {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    int id;
    String name;
    int isActive;

    public Facility(String name, int isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public int getIsActive() {
        return isActive;
    }
}
