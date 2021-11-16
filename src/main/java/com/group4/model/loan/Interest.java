package com.group4.model.loan;

public class Interest {
    private int id;
    private String name;
    private int percentage;

    public Interest(int id, String name, int percentage) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
    }

    public Interest() {
    }

    public Interest(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
