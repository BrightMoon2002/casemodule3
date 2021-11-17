package com.group4.service.spendingService;

import com.group4.model.account.Account;
import com.group4.model.financial.Spending;
import com.group4.service.IService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SpendingDAO extends IService<Spending> {
    public List<Spending> sortByAmount() throws SQLException;
    public List<Spending> findByDate(Date date) throws SQLException;
    public List<Spending> findByDateOfAccountId(Date date,int account_id) throws SQLException;
    public List<Spending> findAllSpendingByAccountId(int account_id) throws SQLException;
    public List<Spending> sortByAmountOfAccountId(int account_id) throws SQLException;
}
