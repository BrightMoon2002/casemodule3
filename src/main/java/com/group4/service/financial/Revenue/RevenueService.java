package com.group4.service.financial.Revenue;


import com.group4.model.account.Account;
import com.group4.model.financial.Revenue;
import com.group4.service.accountService.AccountService;
import com.group4.service.accountService.IAccountService;
import config.SingletonConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RevenueService implements IRevenueService {
    Connection connection = SingletonConnection.getConnection();
     private static final String SELECT_ALL_REVENUES = "select * from revenue;";
     private static final String INSERT_REVENUE_SQL = "insert into revenue (type, amount, date, description, account_id) values (?, ?, ?, ?, ?);";
     private static final String SELECT_REVENUE_BY_ID = "select * from revenue where id = ?;";
     private static final String UPDATE_REVENUE= "update revenue set type = ?, amount = ?, date = ?, description = ?, account_id = ? where id = ?;";
     private static final String DELETE_REVENUE_SQL = "delete from revenue where id=?;";
    private static final String SELECT_REVENUES_BY_ACCOUNT_ID = "select * from revenue where account_id = ?;";
    private static final String SELECT_REVENUES_NOT_BY_ACCOUNT_ID = "select * from revenue where not account_id = ?;";
    IAccountService accountService = new AccountService();


    @Override
    public List<Revenue> findAll() {
        List<Revenue> revenues = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REVENUES);
            System.out.println(SELECT_ALL_REVENUES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                int account_id = resultSet.getInt("account_id");

                Account account = accountService.findById(account_id);


                revenues.add(new Revenue(id, type, description, amount, date, account));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenues;
    }

    @Override
    public void save(Revenue revenue) {
        System.out.println(INSERT_REVENUE_SQL);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVENUE_SQL);
            preparedStatement.setString(1, revenue.getType());
            preparedStatement.setDouble(2, revenue.getAmount());
            preparedStatement.setDate(3, revenue.getDate());
            preparedStatement.setString(4, revenue.getDescription());
            preparedStatement.setInt(5, revenue.getAccount().getId());

            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Revenue findById(int id) {
        System.out.println(SELECT_REVENUE_BY_ID);
        Revenue revenue = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVENUE_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                int account_id = resultSet.getInt("account_id");

                Account account = accountService.findById(account_id);

                revenue = new Revenue(id, type, description, amount, date, account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return revenue;
    }

    @Override
    public boolean update(Revenue revenue) {
        System.out.println(UPDATE_REVENUE);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVENUE);
            preparedStatement.setString(1, revenue.getType());
            preparedStatement.setDouble(2, revenue.getAmount());
            preparedStatement.setDate(3, revenue.getDate());
            preparedStatement.setString(4, revenue.getDescription());
            preparedStatement.setInt(5, revenue.getAccount().getId());
            preparedStatement.setInt(6, revenue.getId());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println(DELETE_REVENUE_SQL);
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVENUE_SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            result = true;


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Revenue> findAllByAccountId(int account_id) {
        List<Revenue> revenues = new ArrayList<>();
        System.out.println(SELECT_REVENUES_BY_ACCOUNT_ID);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVENUES_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =  resultSet.getInt("id");
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");

                Account account = accountService.findById(account_id);

                revenues.add(new Revenue(id, type, description, amount, date, account));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenues;
    }

    @Override
    public List<Revenue> findAllNotByAccountId(int account_id) {
        List<Revenue> revenues = new ArrayList<>();
        System.out.println(SELECT_REVENUES_NOT_BY_ACCOUNT_ID);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVENUES_NOT_BY_ACCOUNT_ID);
            preparedStatement.setInt(1, account_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =  resultSet.getInt("id");
                String type = resultSet.getString("type");
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                String description = resultSet.getString("description");
                int account_user_id = resultSet.getInt("account_id");

                Account account = accountService.findById(account_user_id);

                revenues.add(new Revenue(id, type, description, amount, date, account));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return revenues;
    }


}
