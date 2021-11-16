package com.group4.service;

import java.sql.SQLException;
import java.util.List;

public interface IService<T> {
    List<T> findAll() throws SQLException;

    void save(T t);

    T findById(int id) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean deleteById(int id) throws SQLException;

}
