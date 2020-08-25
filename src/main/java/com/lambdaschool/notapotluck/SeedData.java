package com.lambdaschool.notapotluck;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.services.FoodService;
import com.lambdaschool.notapotluck.services.GuestService;
import com.lambdaschool.notapotluck.services.PotluckService;
import com.lambdaschool.notapotluck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

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
    private PotluckService potluckService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GuestService guestService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        potluckService.deleteAll();
        foodService.deleteAll();
        guestService.deleteAll();

        Food f1 = new Food("pizza");
        f1 = foodService.save(f1);
        Food f2 = new Food("wine");
        f2 = foodService.save(f2);
        Food f3 = new Food("cheese board");
        f3 = foodService.save(f3);

        Guest g1 = new Guest("Alex",
            "Thilen",
            "alex@alex.com");
        g1 = guestService.save(g1);

        Guest g2 = new Guest("Adrienne",
            "Emick",
            "adrienne@adrienne.com");
        g2 = guestService.save(g2);
        Guest g3 = new Guest("Diane",
            "Emick",
            "diane@diane.com");
        g3 = guestService.save(g3);

        User u1 = new User("laurenemick",
            "password",
            "lauren@lauren.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");
        u1 = userService.save(u1);


        Set<PotluckFoods> potluckFoods = new HashSet<>();
        potluckFoods.add(new PotluckFoods(new Potluck(), f1));

        Set<PotluckGuests> potluckGuests = new HashSet<>();
        potluckGuests.add(new PotluckGuests(new Potluck(), g1));

        Potluck p1 = new Potluck(u1,
            "Lunch at Gasworks",
            "09/01/2020",
            "11:30am",
            "Gasworks park",
            "North side, look for red umbrella");
        p1.setFoods(potluckFoods);
        p1.setGuests(potluckGuests);
        p1 = potluckService.save(p1);


        potluckFoods = new HashSet<>();
        potluckFoods.add(new PotluckFoods(new Potluck(), f2));

        potluckGuests = new HashSet<>();
        potluckGuests.add(new PotluckGuests(new Potluck(), g2));

        Potluck p2 = new Potluck(u1,
            "Halloween Party",
            "10/31/2020",
            "4:00pm",
            "1111 90th pl ne, Seattle WA",
            "Black and orange balloons by gate");
        p2.setFoods(potluckFoods);
        p2.setGuests(potluckGuests);
        p2 = potluckService.save(p2);
    }
}
