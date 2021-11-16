package com.group4.service.accountService;

import com.group4.model.account.Account;
import com.group4.service.IService;

import java.util.List;

public interface IAccountService extends IService<Account> {
    @Override
    List<Account> findAll();

    @Override
    void save(Account account);

    @Override
    Account findById(int id);

    @Override
    boolean update(Account account);

    @Override
    boolean deleteById(int id);
}
