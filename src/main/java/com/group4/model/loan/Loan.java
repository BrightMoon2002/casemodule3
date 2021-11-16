package com.group4.model.loan;

import com.group4.model.account.Account;

import java.time.LocalDate;

public class Loan {
    private int id;
    private LocalDate startOfLoan;
    private LocalDate endOfLoan;
    private double amount;
    private Account account;
    private Interest interest;
    private Loan_Status status;

    public Loan(int id, LocalDate startOfLoan, LocalDate endOfLoan, double amount, Account account, Interest interest, Loan_Status status) {
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

    public Loan(LocalDate startOfLoan, LocalDate endOfLoan, double amount, Account account, Interest interest, Loan_Status status) {
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

    public LocalDate getStartOfLoan() {
        return startOfLoan;
    }

    public void setStartOfLoan(LocalDate startOfLoan) {
        this.startOfLoan = startOfLoan;
    }

    public LocalDate getEndOfLoan() {
        return endOfLoan;
    }

    public void setEndOfLoan(LocalDate endOfLoan) {
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
}
