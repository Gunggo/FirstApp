package com.example.viewmodelex.Exercieses;

public class ExerciesesItem {
    private String exerName;
    private String category;

    public ExerciesesItem(String exerName, String category) {
        this.exerName = exerName;
        this.category = category;
    }

    public ExerciesesItem() {

    }

    public String getExerName() {
        return exerName;
    }

    public void setExerName(String exerName) {
        this.exerName = exerName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

}