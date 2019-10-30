package com.example.viewmodelex.WorkOut;

public class WorkOutItem {
    private String kilogram;
    private String rep;
    private String set;

    public WorkOutItem(String kilogram, String rep, String set) {
        this.kilogram = kilogram;
        this.rep = rep;
        this.set = set;
    }

    public WorkOutItem() {

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
