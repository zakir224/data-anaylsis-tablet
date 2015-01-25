package com.nhf.app;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by Zakir on 7/14/2014.
 */
public class Patient {

    private String patientName;
    private int patientAge;
    private String patientId;
    private JSONArray records;

    public Patient(String patientId,String patientName, int patientAge) {
        this.patientName = patientName;
        this.patientAge = patientAge;
        this.patientId = patientId;
        records = new JSONArray();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(int patientAge) {
        this.patientAge = patientAge;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public JSONArray getRecords() {
        return records;
    }

    public void setRecords(JSONArray records) {
        this.records = records;
    }
}
