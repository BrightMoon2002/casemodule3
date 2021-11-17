package com.group4.service.loan;

import com.group4.model.account.Account;
import com.group4.model.account.Role;
import com.group4.model.loan.Interest;
import com.group4.model.loan.Loan;
import com.group4.model.loan.Loan_Status;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class ServiceLoan implements ILoanService{
    public static final String INSERT_LOAN = "INSERT INTO loan (account_id, startOfLoan, endOfLoan, interest_id, amount, status_id) VALUE (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_LOAN = "SELECT * FROM loan JOIN account a ON loan.account_id = a.id JOIN interest ON loan.interest_id = interest.id JOIN loan_status ON loan.status_id = loan_status.id join role on a.role_id = role.id";
    public static final String SELECT_ALL_LOAN_BY_ID = "SELECT * FROM loan JOIN account a ON loan.account_id = a.id JOIN interest ON loan.interest_id = interest.id JOIN loan_status ON loan.status_id = loan_status.id join role on a.role_id = role.id where a.id = ?";
    public static final String SELECT_LOAN_BY_ID = "SELECT * FROM loan JOIN account a ON loan.account_id = a.id JOIN interest ON loan.interest_id = interest.id JOIN loan_status ON loan.status_id = loan_status.id join role on a.role_id = role.id WHERE loan.id = ?";
    public static final String UPDATE_LOAN = "UPDATE loan set account_id = ?, startOfLoan = ?, endOfLoan = ?, interest_id = ?, amount = ?, status_id = ? WHERE id = ?";
    public static final String DELETE_LOAN_BY_ID = "DELETE FROM loan where id = ?";
    private static Connection connection = SingletonConnection.getConnection();



    @Override
    public List<Loan> findAll() {
        List<Loan> loanList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOAN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { int idLoan = resultSet.getInt("id");
                int idAccount = resultSet.getInt("account_id");
                Date startOfLoan = resultSet.getDate("startOfLoan");
                Date endOfLoan = resultSet.getDate("endOfLoan");
                int id_interest = resultSet.getInt("interest_id");
                double amount = resultSet.getDouble("amount");
                int idStatus = resultSet.getInt("status_id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("a.name");
                String nameInterest = resultSet.getString("interest.name");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                int idLoanStatus = resultSet.getInt("loan_status.id");
                String nameLoanStatus = resultSet.getString("loan_status.name");
                String roleName = resultSet.getString("role.name");
                boolean status = resultSet.getBoolean("status");
                int role_id = resultSet.getInt("role_id");
                int percentage = resultSet.getInt("percentage");
                Role role = new Role(role_id, roleName);
                Account account = new Account(idAccount, username, password, name, dob, email, address, status, role);
                Interest interest = new Interest(id_interest, nameInterest, percentage);
                Loan_Status loan_status = new Loan_Status(idLoanStatus, nameLoanStatus);
                Loan loan = new Loan(idLoan, startOfLoan, endOfLoan, amount, account, interest, loan_status);
               loanList.add(loan);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loanList;
    }
    @Override
    public List<Loan> findAllById(int id) {
        List<Loan> loanList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOAN_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) { int idLoan = resultSet.getInt("id");
                int idAccount = resultSet.getInt("account_id");
                Date startOfLoan = resultSet.getDate("startOfLoan");
                Date endOfLoan = resultSet.getDate("endOfLoan");
                int id_interest = resultSet.getInt("interest_id");
                double amount = resultSet.getDouble("amount");
                int idStatus = resultSet.getInt("status_id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("a.name");
                String nameInterest = resultSet.getString("interest.name");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                int idLoanStatus = resultSet.getInt("loan_status.id");
                String nameLoanStatus = resultSet.getString("loan_status.name");
                String roleName = resultSet.getString("role.name");
                boolean status = resultSet.getBoolean("status");
                int role_id = resultSet.getInt("role_id");
                int percentage = resultSet.getInt("percentage");
                Role role = new Role(role_id, roleName);
                Account account = new Account(idAccount, username, password, name, dob, email, address, status, role);
                Interest interest = new Interest(id_interest, nameInterest, percentage);
                Loan_Status loan_status = new Loan_Status(idLoanStatus, nameLoanStatus);
                Loan loan = new Loan(idLoan, startOfLoan, endOfLoan, amount, account, interest, loan_status);
               loanList.add(loan);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loanList;
    }

    @Override
    public void save(Loan loan) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LOAN);
            preparedStatement.setInt(1, loan.getAccount().getId());
            preparedStatement.setDate(2, loan.getStartOfLoan());
            preparedStatement.setDate(3, loan.getEndOfLoan());
            preparedStatement.setInt(4, loan.getInterest().getId());
            preparedStatement.setDouble(5, loan.getAmount());
            preparedStatement.setInt(6, loan.getStatus().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public Loan findById(int id) {
        Loan  loan = new Loan();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LOAN_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int idLoan = resultSet.getInt("id");
                int idAccount = resultSet.getInt("account_id");
                Date startOfLoan = resultSet.getDate("startOfLoan");
                Date endOfLoan = resultSet.getDate("endOfLoan");
                int id_interest = resultSet.getInt("interest_id");
                double amount = resultSet.getDouble("amount");
                int idStatus = resultSet.getInt("status_id");
                String username = resultSet.getString("username");
                String name = resultSet.getString("a.name");
                String nameInterest = resultSet.getString("interest.name");
                String password = resultSet.getString("password");
                String address = resultSet.getString("address");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                int idLoanStatus = resultSet.getInt("loan_status.id");
                String nameLoanStatus = resultSet.getString("loan_status.name");
                String roleName = resultSet.getString("role.name");
                boolean status = resultSet.getBoolean("status");
                int role_id = resultSet.getInt("role_id");
                int percentage = resultSet.getInt("percentage");
                Role role = new Role(role_id, roleName);
                Account account = new Account(idAccount, username, password, name, dob, email, address, status, role);
                Interest interest = new Interest(id_interest, nameInterest, percentage);
                Loan_Status loan_status = new Loan_Status(idLoanStatus, nameLoanStatus);
                loan = new Loan(idLoan, startOfLoan, endOfLoan, amount, account, interest, loan_status);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loan;
    }

    @Override
    public boolean update(Loan loan) {
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOAN);
            preparedStatement.setInt(1, loan.getAccount().getId());
            preparedStatement.setDate(2, loan.getStartOfLoan());
            preparedStatement.setDate(3, loan.getEndOfLoan());
            preparedStatement.setInt(4, loan.getInterest().getId());
            preparedStatement.setDouble(5, loan.getAmount());
            preparedStatement.setInt(6, loan.getStatus().getId());
            preparedStatement.setInt(7, loan.getId());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rowDelete = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LOAN_BY_ID);
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }
}
