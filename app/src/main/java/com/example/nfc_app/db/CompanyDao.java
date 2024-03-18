package com.example.nfc_app.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CompanyDao {
    @Insert
    void insert(Company company);

    @Update
    void update(Company company);

    @Query("SELECT * FROM company WHERE id = :id")
    Company getCompanyById(int id);
}
