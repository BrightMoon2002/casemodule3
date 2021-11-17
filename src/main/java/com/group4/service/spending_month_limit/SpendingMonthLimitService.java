package com.group4.service.spending_month_limit;

import com.group4.model.account.Account;
import com.group4.model.financial.Revenue;
import com.group4.model.limit.SpendingMonthLimit;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import config.SingletonConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpendingMonthLimitService implements ISpendingMonthLimitService{
    Connection connection = SingletonConnection.getConnection();
    private static final String SELECT_ALL_LIMITS = "select * from spending_month_limit;";
    private static final String INSERT_LIMIT_SQL = "insert into spending_month_limit (amount, account_id) values (?, ?);";
    private static final String SELECT_LIMIT_BY_ID = "select * from spending_month_limit where id = ?;";
    private static final String UPDATE_LIMIT= "update spending_month_limit set amount = ?, account_id = ? where id = ?;";
    private static final String DELETE_LIMIT_SQL = "delete from spending_month_limit where id=?;";
    IAccountService accountService = new AccountService();


    @Override
    public List<SpendingMonthLimit> findAll() throws SQLException {
        List<SpendingMonthLimit> limits = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LIMITS);
            System.out.println(SELECT_ALL_LIMITS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double amount = resultSet.getInt("amount");
                int account_id = resultSet.getInt("account_id");

                Account account = accountService.findById(account_id);


                limits.add(new SpendingMonthLimit(id, amount, account));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return limits;
    }

    @Override
    public void save(SpendingMonthLimit limit) {
        System.out.println(INSERT_LIMIT_SQL);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LIMIT_SQL);
            preparedStatement.setDouble(1, limit.getAmount());
            preparedStatement.setInt(2, limit.getAccount().getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public SpendingMonthLimit findById(int id) throws SQLException {
        System.out.println(SELECT_LIMIT_BY_ID);
        SpendingMonthLimit limit = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LIMIT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                int account_id = resultSet.getInt("account_id");

                Account account = accountService.findById(account_id);

                limit = new SpendingMonthLimit(id, amount, account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return limit;
    }

    @Override
    public boolean update(SpendingMonthLimit limit) throws SQLException {
        System.out.println(UPDATE_LIMIT);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LIMIT);
            preparedStatement.setDouble(1, limit.getAmount());
            preparedStatement.setInt(2, limit.getAccount().getId());
            preparedStatement.setInt(3, limit.getId());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        System.out.println(DELETE_LIMIT_SQL);
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LIMIT_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            result = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}

