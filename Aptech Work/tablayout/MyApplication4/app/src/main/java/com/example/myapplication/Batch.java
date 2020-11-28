package com.example.myapplication;

import java.util.ArrayList;

public class Batch {

    public  String batchCode;

    public ArrayList<String> lstStudents;

    public Batch(String batchCode, ArrayList<String> lstStudents) {
        this.batchCode = batchCode;
        this.lstStudents = lstStudents;
    }
}
