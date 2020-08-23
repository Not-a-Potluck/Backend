package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    // connects service to user table
    @Autowired
    private UserRepository userrepos;
}
