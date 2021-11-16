package com.group4.service.loan;

import com.group4.model.loan.Interest;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InterestService implements IInterestService{
    private Connection connection = SingletonConnection.getConnection();
    @Override
    public List<Interest> findAll()  {
        List<Interest> interestList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM interest;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int percentage = resultSet.getInt("percentage");
                interestList.add(new Interest(id, name, percentage));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return interestList ;
    }

    @Override
    public void save(Interest interest) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO interest (name, percentage) VALUE (?, ?)");
            preparedStatement.setString(1, interest.getName());
            preparedStatement.setInt(2, interest.getPercentage());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Interest findById(int id) {
        Interest interest = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM interest where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInterest = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int percentage = resultSet.getInt("percentage");
                interest = new Interest(idInterest, name, percentage);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return interest;
    }

    @Override
    public boolean update(Interest interest){
        boolean rowUpdate = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE interest set name = ?, percentage = ?");
            preparedStatement.setString(1, interest.getName());
            preparedStatement.setInt(2, interest.getPercentage());
            rowUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public boolean deleteById(int id) {
        boolean rowDelete =false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from interest where id = ?");
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }
}
