package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.User;
import com.lambdaschool.notapotluck.models.UserMinimum;

import java.util.List;

public interface UserService
{
    List<User> findAll();

    User findUserById(long id);

    User save(User user);

    User update(UserMinimum user, long id);

    public void deleteAll();
}
