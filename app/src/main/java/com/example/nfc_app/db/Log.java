package com.example.nfc_app.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Log {
    @PrimaryKey(autoGenerate = true)
    @NotNull
    int id;
    String tag_num;
    String date;
    int facility_id;
    int control_num;
    String control_num_status;
    int work_id;
    int company_id;
    String uuid;

    public Log(String tag_num, String date, int facility_id, int control_num, String control_num_status, int work_id, int company_id, String uuid) {
        this.tag_num = tag_num;
        this.date = date;
        this.facility_id = facility_id;
        this.control_num = control_num;
        this.control_num_status = control_num_status;
        this.work_id = work_id;
        this.company_id = company_id;
        this.uuid = uuid;
    }

    public String getTag_num() {
        return tag_num;
    }

    public String getDate() {
        return date;
    }

    public int getFacility_id() {
        return facility_id;
    }

    public int getControl_num() {
        return control_num;
    }

    public String getControl_num_status() {
        return control_num_status;
    }

    public int getWork_id() {
        return work_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public String getUuid() {
        return uuid;
    }
}
