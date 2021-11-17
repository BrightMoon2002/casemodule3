package com.group4.model.financial;

import com.group4.model.account.Account;

import java.sql.Date;

public class Spending {
    private int id;
    private String type;
    private String description;
    private double amount;
    private Date date;
    private Account account;

    public Spending() {
    }

    public Spending(String type, String description, double amount, Date date) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public Spending(String type, String description, double amount, Date date, Account account) {
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    public Spending(int id, String type, String description, double amount, Date date, Account account) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.account =account;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
