package com.lambdaschool.notapotluck;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    private UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    private PotluckService potluckService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GuestService guestService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        // reset data
        potluckService.deleteAll();
        foodService.deleteAll();
        guestService.deleteAll();
        roleService.deleteAll();
        userService.deleteAll();

        Role r1 = new Role("user");
        r1 = roleService.save(r1);

        //        Guest g1 = new Guest("Alex",
        //            "Thilen",
        //            "alex@alex.com");
        //        g1 = guestService.save(g1);
        //
        //        Guest g2 = new Guest("Adrienne",
        //            "Emick",
        //            "adrienne@adrienne.com");
        //        g2 = guestService.save(g2);
        //        Guest g3 = new Guest("Diane",
        //            "Emick",
        //            "diane@diane.com");
        //        g3 = guestService.save(g3);

        User u1 = new User("laurenemick",
            "password",
            "lauren@lauren.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        u1.getRoles().add(new UserRoles(u1, r1));
        u1 = userService.save(u1);

        Potluck p1 = new Potluck(u1,
            "Lunch at Gasworks",
            "09/01/2020",
            "11:30am",
            "Gasworks park",
            "North side, look for red umbrella");
        //        PotluckGuests pg1 = new PotluckGuests(p1, g1);

        p1.getFoods().add(new Food(p1,"pizza"));
        p1.getFoods().add(new Food(p1,"salad"));

        p1.getGuests().add(new Guest(p1,"Alex", "Thilen", "alex@alex.com"));
        p1.getGuests().add(new Guest(p1,"Adrienne", "Emick", "adj@adj.com"));

        //        Set<PotluckGuests> potluckGuests = new HashSet<>();
        //        potluckGuests.add(new PotluckGuests(p1, g1));

        //        p1.setGuests(potluckGuests);
//        p1 = potluckService.save(u1.getUserid(), p1);


        User u2 = new User("hannah",
            "password",
            "hannah@hannah.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        u2.getRoles().add(new UserRoles(u2, r1));
        u2 = userService.save(u2);

        Potluck p2 = new Potluck(u2,
            "Halloween Party",
            "10/31/2020",
            "4:00pm",
            "1111 90th pl ne, Seattle WA",
            "Black and orange balloons by gate");

        p2.getFoods().add(new Food(p2,"burgers"));
        p2.getFoods().add(new Food(p2,"salad"));

        p2.getGuests().add(new Guest(p1,"harry", "harry", "harry@harry.com"));
        p2.getGuests().add(new Guest(p1,"fatima", "fatima", "fatima@fatima.com"));

//        p2 = potluckService.save(u2.getUserid(), p2);
    }
}
