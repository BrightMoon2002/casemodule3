package com.group4.model.loan;

import com.group4.model.account.Account;



import java.sql.Date;

public class Loan {
    private int id;
    private Date startOfLoan;
    private Date endOfLoan;
    private double amount;
    private Account account;
    private Interest interest;
    private Loan_Status status;

    public Loan(int id, Date startOfLoan, Date endOfLoan, double amount, Account account, Interest interest, Loan_Status status) {
        this.id = id;
        this.startOfLoan = startOfLoan;
        this.endOfLoan = endOfLoan;
        this.amount = amount;
        this.account = account;
        this.interest = interest;
        this.status = status;
    }

    public Loan() {
    }

    public Loan(Date startOfLoan, Date endOfLoan, double amount, Account account, Interest interest, Loan_Status status) {
        this.startOfLoan = startOfLoan;
        this.endOfLoan = endOfLoan;
        this.amount = amount;
        this.account = account;
        this.interest = interest;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartOfLoan() {
        return startOfLoan;
    }

    public void setStartOfLoan(Date startOfLoan) {
        this.startOfLoan = startOfLoan;
    }

    public Date getEndOfLoan() {
        return endOfLoan;
    }

    public void setEndOfLoan(Date endOfLoan) {
        this.endOfLoan = endOfLoan;
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

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public Loan_Status getStatus() {
        return status;
    }

    public void setStatus(Loan_Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", startOfLoan=" + startOfLoan +
                ", endOfLoan=" + endOfLoan +
                ", amount=" + amount +
                ", account=" + account +
                ", interest=" + interest +
                ", status=" + status +
                '}';
    }
}
