package com.example.nfc_app.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Log.class}, version = 1)
public abstract class LogDb extends RoomDatabase {
    public abstract LogDao getLogDao();
}
