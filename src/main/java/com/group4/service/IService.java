package com.group4.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();

    void save(T t);

    T findById(int id);

    boolean update(T t);

    boolean deleteById(int id);

}
