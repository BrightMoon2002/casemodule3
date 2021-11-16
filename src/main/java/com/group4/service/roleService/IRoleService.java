package com.group4.service.roleService;

import com.group4.model.account.Role;
import com.group4.service.IService;

import java.util.List;

public interface IRoleService extends IService<Role> {
    @Override
    List<Role> findAll();

    @Override
    void save(Role role);

    @Override
    Role findById(int id);

    @Override
    boolean update(Role role);

    @Override
    boolean deleteById(int id);
}
