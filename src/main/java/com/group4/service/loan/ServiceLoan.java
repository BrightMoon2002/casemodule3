package com.group4.service.loan;

import com.group4.model.loan.Loan;
import config.SingletonConnection;

import java.sql.Connection;
import java.util.List;

public class ServiceLoan implements ILoanService{
    private static Connection connection = SingletonConnection.getConnection();
    @Override
    public List<Loan> findAll() {
        return null;
    }

    @Override
    public void save(Loan loan) {

    }

    @Override
    public Loan findById(int id) {
        return null;
    }

    @Override
    public boolean update(Loan loan) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
