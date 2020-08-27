package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Potluck;

import java.util.List;

public interface FoodService
{
    Food save(Food food);

    public void deleteAll();

    Food findFoodById(long id);

    List<Food> findAll();

//    void addFood(Food newFood, Potluck potluck);

//    Food claim(long foodid, long guestid);
//
//    Food unclaim(long foodid, long guestid);
}
