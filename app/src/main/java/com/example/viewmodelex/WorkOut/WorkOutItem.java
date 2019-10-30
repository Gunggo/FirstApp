package com.example.viewmodelex.WorkOut;

public class WorkOutItem {
    private String title;
    private String kilogram;
    private String rep;
    private String set;
    private String date;

    public WorkOutItem(String kilogram, String rep, String set) {
        this.kilogram = kilogram;
        this.rep = rep;
        this.set = set;
    }

    public WorkOutItem() {

    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
