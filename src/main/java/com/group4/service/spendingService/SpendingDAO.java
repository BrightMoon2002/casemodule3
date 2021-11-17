package com.group4.service.spendingService;

import com.group4.model.financial.Spending;
import com.group4.service.IService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface SpendingDAO extends IService<Spending> {
    public List<Spending> sortByAmount() throws SQLException;
    public List<Spending> findByDate(Date date) throws SQLException;
}
