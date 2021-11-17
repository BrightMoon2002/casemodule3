package com.group4.service.spendingService;

import com.group4.model.account.Account;
import com.group4.model.financial.Spending;
import com.group4.service.accountService.AccountService;
import config.SingletonConnection;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ISpendingDAO implements SpendingDAO {
    private AccountService accountService = new AccountService();
    private Spending spending = new Spending();
    private static Connection connection = SingletonConnection.getConnection();
    private Account account = new Account();
    private static final String INSERT_SPENDING_SQL = "INSERT INTO spending (type,amount,date,description,account_id) VALUES (?, ?, ?,?)";
    private static final String SELECT_ALL_SPENDING = "select * from spending";
    private static final String SELECT_SPENDING_BY_ID = "select id,type,amount,date,description,account_id from spending where id =?";
    private static final String UPDATE_SPENDING_SQL = "update spending set type = ?,amount= ?, date =?, description=? where id = ?";
    private static final String DELETE_SPENDING_SQL = "delete from spending where id = ?";
    public static final String SELECT_FROM_SPENDING_ORDER_BY_AMOUNT = "select *from spending order by amount";
    public static final String SELECT_FROM_SPENDING_ORDER_BY_AMOUNT_OF_ACCOUNT_ID = "select *from spending where account_id=? order by amount desc ";
    public static final String SELECT_FROM_SPENDING_BY_DATE = "select *from spending where date =? order by amount desc";
    public static final String SELECT_FROM_SPENDING_BY_DATE_OF_ACCOUNT_ID = "select *from spending where date =? and account_id=? order by amount desc";
    public static final String SELECT_ALL_SPENDING_BY_ACCOUNT_ID = "select * from spending where account_id =?";

    @Override
    public List<Spending> findAll() throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SPENDING);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_spending = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            Date date = resultSet.getDate("date");
            int id_account = resultSet.getInt("account_id");
            account = accountService.findById(id_account);
            spendings.add(new Spending(id_spending, type, description, amount, date, account));
        }
        return spendings;
    }

    @Override
    public void save(Spending spending) {
        System.out.println(INSERT_SPENDING_SQL);
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SPENDING_SQL)) {
            preparedStatement.setString(1, spending.getType());
            preparedStatement.setDouble(2, spending.getAmount());
            preparedStatement.setDate(3, spending.getDate());
            preparedStatement.setString(4, spending.getDescription());
            preparedStatement.setInt(5, spending.getId());
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
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SPENDING_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            String type = rs.getString("type");
            Double amount = rs.getDouble("amount");
            Date date = rs.getDate("date");
            String description = rs.getString("description");
            int account_id = rs.getInt("account_id");
            account = accountService.findById(account_id);
            spending = new Spending(type, description, amount, date, account);
        }
        return spending;
    }

    @Override
    public boolean update(Spending spending) throws SQLException {
        boolean update;
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPENDING_SQL);
        preparedStatement.setString(1, spending.getType());
        preparedStatement.setDouble(2, spending.getAmount());
        preparedStatement.setDate(3, spending.getDate());
        preparedStatement.setString(4, spending.getDescription());
        preparedStatement.setInt(5, spending.getId());
        update = preparedStatement.executeUpdate() > 0;
        return update;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        boolean delete;
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SPENDING_SQL);
        preparedStatement.setInt(1, id);
        delete = preparedStatement.executeUpdate() > 0;
        return delete;
    }

    @Override
    public List<Spending> sortByAmount() throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SPENDING_ORDER_BY_AMOUNT);
        System.out.println(preparedStatement);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_spending = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            Date date = resultSet.getDate("date");
            int id_account = resultSet.getInt("account_id");
            account = accountService.findById(id_account);
            spendings.add(new Spending(id_spending, type, description, amount, date, account));
        }
        return spendings;
    }

    @Override
    public List<Spending> findByDate(Date date) throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SPENDING_BY_DATE);
        preparedStatement.setDate(1, date);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_spending = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            int id_account = resultSet.getInt("account_id");
            account = accountService.findById(id_account);
            spendings.add(new Spending(id_spending, type, description, amount, date, account));
        }
        return spendings;
    }

    @Override
    public List<Spending> findByDateOfAccountId(Date date, int account_id) throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SPENDING_BY_DATE_OF_ACCOUNT_ID);
        preparedStatement.setDate(1, date);
        preparedStatement.setInt(2, account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_spending = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            int id_account = resultSet.getInt("account_id");
            account = accountService.findById(id_account);
            spendings.add(new Spending(id_spending, type, description, amount, date, account));
        }
        return spendings;
    }

    @Override
    public List<Spending> findAllSpendingByAccountId(int account_id) throws SQLException {
        List<Spending> spendingList = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SPENDING_BY_ACCOUNT_ID);
        preparedStatement.setInt(1, account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            Date date = resultSet.getDate("date");
            String description = resultSet.getString("description");
            Account account = accountService.findById(account_id);
            spendingList.add(new Spending(id, type, description, amount, date, account));
        }
        return spendingList;
    }

    @Override
    public List<Spending> sortByAmountOfAccountId(int account_id) throws SQLException {
        List<Spending> spendings = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_SPENDING_ORDER_BY_AMOUNT_OF_ACCOUNT_ID);
        preparedStatement.setInt(1, account_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id_spending = resultSet.getInt("id");
            String type = resultSet.getString("type");
            Double amount = resultSet.getDouble("amount");
            String description = resultSet.getString("description");
            Date date = resultSet.getDate("date");
            int id_account = resultSet.getInt("account_id");
            account = accountService.findById(id_account);
            spendings.add(new Spending(id_spending, type, description, amount, date, account));
        }
        return spendings;
    }


}
