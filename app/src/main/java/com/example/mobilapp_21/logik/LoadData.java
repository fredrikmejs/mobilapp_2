package com.example.mobilapp_21.logik;

import java.util.ArrayList;

public class LoadData {
    private static final LoadData ourInstance = new LoadData();
    private ArrayList<String> sheet1 = new ArrayList<>();
    private ArrayList<String> sheet2 = new ArrayList<>();
    private ArrayList<String> sheet3 = new ArrayList<>();
    private ArrayList<String> wordDR = new ArrayList<>();
    private String name;


    public static LoadData getInstance() {
        return ourInstance;
    }

    private LoadData() {
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public ArrayList getSheet(String value) {

        if (value.equals("1")) {
            return sheet1;
        } else if (value.equals("2")) {
            return sheet2;
        } else return sheet3;
    }

    public ArrayList<String> getWordDR(){

        return wordDR;
    }

    public void setSheet1(ArrayList<String> arr){
        sheet1.clear();

        sheet1.addAll(arr);

    }

    public void setSheet2(ArrayList<String> arr){
        sheet2.clear();
        sheet2.addAll(arr);

    }

    public void setSheet3(ArrayList<String> arr){
        sheet3.clear();
        sheet3.addAll(arr);

    }

    public void setWordDR(ArrayList<String> arr){
        wordDR.clear();
        wordDR.addAll(arr);
    }







}
