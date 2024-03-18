package com.example.nfc_app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Facility.class, version = 1)
public abstract class FacilityDb extends RoomDatabase {
    public abstract FacilityDao getFacilityDao();
}
