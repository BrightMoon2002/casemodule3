package com.group4.service.accountService;

import com.group4.model.account.Account;
import com.group4.model.account.Role;

import com.group4.service.roleService.IRoleService;
import com.group4.service.roleService.RoleService;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IAccountService {
    private final Connection connection = SingletonConnection.getConnection();
    private final IRoleService roleService = new RoleService();

    private static final String INSERT_ACCOUNT_SQL = "INSERT INTO account (username,password,name,dob,email,address,status,role_id) VALUES (?,?,?,?,?,?,?,?)";
    private static final String SELECT_ACCOUNT_BY_ID = "select * from account where id =?";
    private static final String SELECT_ALL_ACCOUNT = "select * from account";
    private static final String DELETE_ACCOUNT_SQL = "delete from account where id = ?";
    private static final String UPDATE_ACCOUNT_SQL = "update account set username = ?,password = ?,name = ?,dob = ?,email = ?,address = ?,status = ?,role_id = ? where id = ?";

    @Override
    public List<Account> findAll() {
        List<Account> accountList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ACCOUNT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Boolean status = Boolean.parseBoolean(resultSet.getString("status"));
                int role_id = resultSet.getInt("role_id");
                Role role = roleService.findById(role_id);
                accountList.add(new Account(id, username, password, name, dob, email, address, status, role));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accountList;
    }

    @Override
    public void save(Account account) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getDob());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setBoolean(7, account.isStatus());
            preparedStatement.setInt(8, account.getRole().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Account findById(int id) {
        Account account = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                String address = resultSet.getString("address");
                Boolean status = Boolean.parseBoolean(resultSet.getString("status"));
                int role_id = resultSet.getInt("role_id");
                Role role = roleService.findById(role_id);
                account = new Account(id, username, password, name, dob, email, address, status, role);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    @Override
    public boolean update(Account account) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getName());
            preparedStatement.setString(4, account.getDob());
            preparedStatement.setString(5, account.getEmail());
            preparedStatement.setString(6, account.getAddress());
            preparedStatement.setBoolean(7, account.isStatus());
            preparedStatement.setInt(8, account.getRole().getId());
            preparedStatement.setInt(9, account.getId());
            rowUpdate = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rowDelete = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ACCOUNT_SQL);
            preparedStatement.setInt(1,id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

    public Account checkLogin(String username,String password){
        Account account = null;
        List<Account>accountList = findAll();
        for (Account a : accountList) {
            if (a.getUsername().equals(username)
                    && a.getPassword().equals(password)) {
                account = a;
                break;
            }
        }
        return account;
    }
}
