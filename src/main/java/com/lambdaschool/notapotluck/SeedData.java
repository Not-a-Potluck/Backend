package com.lambdaschool.notapotluck;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.services.FoodService;
import com.lambdaschool.notapotluck.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.sampled.Port;

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
    private FoodService foodService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        User u1 = new User("laurenemick",
            "password",
            "lauren@lauren.com",
            "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png");

        Potluck p1 = new Potluck(u1,
           "Lunch at Gasworks",
           "09/01/2020",
           "11:30am",
           "Gasworks park",
           "North side, look for red umbrella");

        Potluck p2 = new Potluck(u1,
            "Halloween Party",
            "10/31/2020",
            "4:00pm",
            "1111 90th pl ne, Seattle WA",
            "Black and orange balloons by gate");

//        p1.getFoods().add(new PotluckFoods(p1, f2));
//        p1.getFoods().add(new PotluckFoods(p1, f3));
//        p2.getFoods().add(new PotluckFoods(p2, f1));
//
        Guest g1 = new Guest("Alex",
            "Thilen",
            "alex@alex.com");
        Guest g2 = new Guest("Adrienne",
            "Emick",
            "adrienne@adrienne.com");
        Guest g3 = new Guest("Diane",
            "Emick",
            "diane@diane.com");

        Food f1 = new Food(p1, g1, "pizza");
        f1 = foodService.save(f1);
        Food f2 = new Food(p1, g2,"wine");
        f2 = foodService.save(f2);
        Food f3 = new Food(p2, g3,"cheese board");
        f3 = foodService.save(f3);
        userService.save(u1);
    }
}
