package com.group4.model.financial;

import com.group4.model.account.Account;

public class Spending_Month_Limit {
    private int id;
    private double amount;
    private Account account;

    public Spending_Month_Limit() {
    }

    public Spending_Month_Limit(double amount, Account account) {
        this.amount = amount;
        this.account = account;
    }

    public Spending_Month_Limit(int id, double amount, Account account) {
        this.id = id;
        this.amount = amount;
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
