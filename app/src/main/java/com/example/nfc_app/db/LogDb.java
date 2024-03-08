package com.example.nfc_app.db;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Log.class}, version = 4)
public abstract class LogDb extends RoomDatabase {
    public abstract LogDao getLogDao();
}
