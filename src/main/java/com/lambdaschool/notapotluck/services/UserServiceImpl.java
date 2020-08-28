package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService
{
    // connects service to user table
    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleService roleService;

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    public User findUserById(long id) throws EntityNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User id " + id + " not found!"));
    }

    @Transactional
    @Override
    public User save(User user)
    {
        User newUser = new User();

        if (user.getUserid() != 0)
        {
            userrepos.findById(user.getUserid())
                .orElseThrow(() -> new EntityNotFoundException("User id " + user.getUserid() + " not found!"));
            newUser.setUserid(user.getUserid());
        }

        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser,
                    addRole));
        }

        newUser.getPotlucks().clear();
        for (Potluck pe : user.getPotlucks())
        {
            newUser.getPotlucks()
                .add(new Potluck(newUser,
                    pe.getEventname(),
                    pe.getDate(),
                    pe.getTime(),
                    pe.getLocation(),
                    pe.getDescription()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user, long id)
    {
        User updateUser = userrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User " + id + " not found!"));

//        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
//        {
//            if (user.getUsername() != null)
//            {
//                currentUser.setUsername(user.getUsername().toLowerCase());
//            }

            if (user.getPassword() != null && user.getPassword() != "")
            {
                updateUser.setPassword(user.getPassword());
            }

            if (user.getPrimaryemail() != null)
            {
                updateUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());
            }

            if (user.getImageurl() != null)
            {
                updateUser.setImageurl(user.getImageurl());
            }
//
//            if (user.getRoles()
//                .size() > 0)
//            {
//                currentUser.getRoles()
//                    .clear();
//                for (UserRoles ur : user.getRoles())
//                {
//                    Role addRole = roleService.findRoleById(ur.getRole()
//                        .getRoleid());
//
//                    currentUser.getRoles()
//                        .add(new UserRoles(currentUser,
//                            addRole));
//                }
//            }
//
//            if (user.getPotlucks().size() > 0)
//            {
//                currentUser.getPotlucks().clear();
//                for (Potluck pe : user.getPotlucks())
//                {
//                    currentUser.getPotlucks()
//                        .add(new Potluck(currentUser,
//                            pe.getEventname(),
//                            pe.getDate(),
//                            pe.getTime(),
//                            pe.getLocation(),
//                            pe.getDescription()));
//                }
//            }

            return userrepos.save(updateUser);
//        } else
//        {
//            // note we should never get to this line but is needed for the compiler
//            // to recognize that this exception can be thrown
//            throw new ResourceNotFoundException("This user is not authorized to make change");
//        }
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new EntityNotFoundException("Username " + name + " not found!");
        }
        return uu;
    }
}
