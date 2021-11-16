package com.group4.service.spending;

import com.group4.model.account.Account;
import com.group4.model.financial.Spending;
import config.SingletonConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ISpendingDAO implements SpendingDAO {
    private Spending spending = new Spending();
    private SingletonConnection singletonConnection = new SingletonConnection();
    private Account account = new Account();
    private static final String INSERT_SPENDING_SQL = "INSERT INTO spending (type,amount,date,description,account_id) VALUES (?, ?, ?,?)";
    private static final String SELECT_ALL_SPENDING = "select * from spending";
    private static final String SELECT_SPENDING_BY_ID = "select id,type,amount,date,description,account_id from spending where id =?";

    @Override
    public List<Spending> findAll() throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        Connection connection = singletonConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SPENDING);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            Date date = resultSet.getDate("date");
            int id = resultSet.getInt("account_id");
            spendings.add(new Spending(type, amount, description, date));
        }
        return spendings;
    }

    @Override
    public void save(Spending spending) {
        System.out.println(INSERT_SPENDING_SQL);
        try (Connection connection = singletonConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPENDING_SQL)) {
            preparedStatement.setInt(1, spending.getId());
            preparedStatement.setString(2, spending.getType());
            preparedStatement.setDouble(3, spending.getAmount());
            preparedStatement.setDate(4, spending.getDate());
            preparedStatement.setString(5, spending.getDescription());
            preparedStatement.setInt(6, spending.getAccount().getId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException e) {
    }

    @Override
    public Spending findById(int id) throws SQLException {
        Spending spending = null;
        Connection connection = singletonConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPENDING_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String type = rs.getString("type");
            Double amount = rs.getDouble("amount");
            Date date = rs.getDate("date");
            String description = rs.getString("description");
            int account_id = rs.getInt("account_id");

            spending = new Spending(type,amount,date,description,)
        }
        return user;
    }

    @Override
    public boolean update(Spending spending) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
