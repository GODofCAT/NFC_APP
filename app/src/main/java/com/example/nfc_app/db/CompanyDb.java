package com.example.nfc_app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Company.class, version = 1)
public abstract class CompanyDb extends RoomDatabase {
    public abstract CompanyDao getCompanyDao();
}
