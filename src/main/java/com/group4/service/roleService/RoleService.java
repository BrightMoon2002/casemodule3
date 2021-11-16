package com.group4.service.roleService;

import com.group4.model.account.Role;
import config.SingletonConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleService implements IRoleService{

    private static final String SELECT_USER_BY_ID = "select * from role where id =?";
    private final Connection connection = SingletonConnection.getConnection();

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public Role findById(int id) {
        Role role = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                role = new Role(id,name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean update(Role role) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
