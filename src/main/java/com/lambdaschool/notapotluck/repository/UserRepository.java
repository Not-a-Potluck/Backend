package com.lambdaschool.notapotluck.repository;

import com.lambdaschool.notapotluck.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
}
