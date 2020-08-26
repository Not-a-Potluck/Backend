package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Role;
import com.lambdaschool.notapotluck.repository.RoleRepository;
import com.lambdaschool.notapotluck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleRepository rolerepos;

    @Autowired
    UserRepository userrepos;

    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        rolerepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return rolerepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Role id " + id + " not found!"));
    }

    @Override
    public Role findByName(String name)
    {
        Role rr = rolerepos.findByNameIgnoreCase(name);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new EntityNotFoundException(name);
        }
    }

    @Transactional
    @Override
    public Role save(Role role)
    {
        if (role.getUsers()
            .size() > 0)
        {
            throw new EntityNotFoundException("User Roles are not updated through Role.");
        }

        return rolerepos.save(role);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        rolerepos.deleteAll();
    }
}
