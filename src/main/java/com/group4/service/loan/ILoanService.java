package com.group4.service.loan;

import com.group4.model.loan.Loan;
import com.group4.service.IService;

import java.util.List;

public interface ILoanService extends IService<Loan> {
    public List<Loan> findAllById(int id);

}
