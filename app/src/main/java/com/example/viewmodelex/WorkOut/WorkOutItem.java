package com.example.viewmodelex.WorkOut;

import java.util.ArrayList;

public class WorkOutItem {
    private String title;
    private String kilogram;
    private String rep;
    private String set;
    private String date;

    private ArrayList<String> kgList;
    private ArrayList<String> repList;

    public WorkOutItem(String kilogram, String rep) {
        this.kilogram = kilogram;
        this.rep = rep;
    }

    public WorkOutItem() {
        kgList = new ArrayList<>();
        repList = new ArrayList<>();
    }

    public void setKgList(ArrayList<String> kgList) {
        this.kgList = kgList;
    }

    public void setRepList(ArrayList<String> repList) {
        this.repList = repList;
    }

    public ArrayList<String> getKgList() {
        return kgList;
    }

    public void addKgList(String kg) {
        kgList.add(kg);
    }

    public ArrayList<String> getRepList() {
        return repList;
    }

    public void addRepList(String rep) {
        repList.add(rep);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }

    public String getKilogram() {
        return kilogram;
    }

    public void setKilogram(String kilogram) {
        this.kilogram = kilogram;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }
}
