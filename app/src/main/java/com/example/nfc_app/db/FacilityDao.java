package com.example.nfc_app.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FacilityDao {
    @Insert
    void insert(Facility facility);

    @Query("SELECT * FROM facility")
    List<Facility> getAllFacilities();

    @Query("DELETE FROM facility")
    void deleteFacilities();
}
