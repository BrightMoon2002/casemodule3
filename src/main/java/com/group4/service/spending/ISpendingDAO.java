package com.group4.service.spending;

import com.group4.model.financial.Spending;

import java.util.List;

public class ISpendingDAO implements SpendingDAO{

    @Override
    public List<Spending> findAll() {
        return null;
    }

    @Override
    public void save(Spending spending) {

    }

    @Override
    public Spending findById(int id) {
        return null;
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
