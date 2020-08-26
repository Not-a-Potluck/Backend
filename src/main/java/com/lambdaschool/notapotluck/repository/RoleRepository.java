package com.lambdaschool.notapotluck.repository;

import com.lambdaschool.notapotluck.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    Role findByNameIgnoreCase(String name);
}
