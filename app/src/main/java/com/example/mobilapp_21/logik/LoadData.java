package com.example.mobilapp_21.logik;

import java.util.ArrayList;

public class LoadData {
    private static final LoadData ourInstance = new LoadData();
    private ArrayList<String> ark1 = new ArrayList<>();
    private ArrayList<String> ark2 = new ArrayList<>();
    private ArrayList<String> ark3 = new ArrayList<>();
    private ArrayList<String> ordDR = new ArrayList<>();


    public static LoadData getInstance() {
        return ourInstance;
    }

    private LoadData() {
    }


    public ArrayList getArk(String value) {

        if (value.equals("1")) {
            return ark1;
        } else if (value.equals("2")) {
            return ark2;
        } else return ark3;
    }

    public ArrayList<String> getOrdDR(){

        return ordDR;
    }

    public void setArk1(ArrayList<String> arr){
        ark1.clear();

        ark1.addAll(arr);

    }

    public void setArk2(ArrayList<String> arr){
        ark2.clear();
        ark2.addAll(arr);

    }

    public void setArk3(ArrayList<String> arr){
        ark3.clear();
        ark3.addAll(arr);

    }

    public void setOrdDR(ArrayList<String> arr){
        ordDR.clear();
        ordDR.addAll(arr);
    }

}
