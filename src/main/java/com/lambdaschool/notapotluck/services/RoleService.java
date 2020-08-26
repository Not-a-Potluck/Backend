package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Role;

import java.util.List;

public interface RoleService
{
    List<Role> findAll();

    Role findRoleById(long id);

    Role findByName(String name);

    Role save(Role role);

    public void deleteAll();
}
