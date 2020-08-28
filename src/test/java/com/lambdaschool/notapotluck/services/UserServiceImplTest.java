package com.lambdaschool.notapotluck.services;

import com.lambdaschool.NotAPotluckApplication;
import com.lambdaschool.notapotluck.models.Role;
import com.lambdaschool.notapotluck.models.User;
import com.lambdaschool.notapotluck.models.UserRoles;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NotAPotluckApplication.class)
public class UserServiceImplTest
{
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        List<User> myList = userService.findAll();
        for (User u : myList)
        {
            System.out.println(u.getUserid() + "" + u.getUsername());
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findAll()
    {
        assertEquals(2, userService.findAll()
            .size());
    }

    @Test
    public void findUserById()
    {
        assertEquals("laurenemick", userService.findUserById(2)
            .getUsername());
    }

    @Test
    public void save()
    {
        Role r1 = new Role("user");
        r1.setRoleid(1);

        User u2 = new User("james",
            "password",
            "james@james.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        u2.getRoles().add(new UserRoles(u2, r1));
        User saveu2 = userService.save(u2);

        assertEquals("james@james.com", userService.findByName("james").getPrimaryemail());
    }

    // yourself or admin are authorized to make updates
    // requires some user security to run -- @WithUserDetails("cinnamon")
    // controllers require authentication/security for all methods
    @Transactional
    @WithUserDetails("laurenemick")
    @Test
    public void update()
    {
        Role r1 = new Role("user");
        r1.setRoleid(1);

        User u1 = new User("laurenemick",
            "password",
            "laurenemick@lauren.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        u1.getRoles().add(new UserRoles(u1, r1));
        User updateu2 = userService.update(u1, 2);

        assertEquals("laurenemick@lauren.com", userService.findByName("laurenemick").getPrimaryemail());
    }

    @Test
    public void deleteAll()
    {
    }
}