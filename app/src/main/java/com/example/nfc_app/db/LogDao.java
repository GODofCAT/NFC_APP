package com.example.nfc_app.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Log log);

    @Query("SELECT * FROM log")
    List<Log> getLogs();

    @Query("SELECT * FROM log WHERE uuid = :luuid")
    Log findByUUID(String luuid);


    @Query("DELETE FROM log WHERE uuid = :luuid")
    void deleteByUUID(String luuid);
}
