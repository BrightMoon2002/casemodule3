package com.group4.model.loan;

public class Loan_Status {
    private int id;
    private String name;

    public Loan_Status() {
    }

    public Loan_Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Loan_Status(String name) {
        this.name = name;
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
}
