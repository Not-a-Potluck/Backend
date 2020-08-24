package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Potluck;
import com.lambdaschool.notapotluck.models.User;
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
        newUser.setPassword(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());

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
        User currentUser = findUserById(id);

//        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
//        {
            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername().toLowerCase());
            }

            if (user.getPassword() != null)
            {
                currentUser.setPassword(user.getPassword());
            }

            if (user.getPrimaryemail() != null)
            {
                currentUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());
            }

            if (user.getPotlucks().size() > 0)
            {
                currentUser.getPotlucks().clear();
                for (Potluck pe : user.getPotlucks())
                {
                    currentUser.getPotlucks()
                        .add(new Potluck(currentUser,
                            pe.getEventname(),
                            pe.getDate(),
                            pe.getTime(),
                            pe.getLocation(),
                            pe.getDescription()));
                }
            }

            return userrepos.save(currentUser);
//        } else
//        {
//            // note we should never get to this line but is needed for the compiler
//            // to recognize that this exception can be thrown
//            throw new ResourceNotFoundException("This user is not authorized to make change");
//        }
    }
}
