package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;

import java.util.List;

public interface FoodService
{
    Food save(Food food);

    public void deleteAll();

    Food findFoodById(long id);

    List<Food> findAll();

//    Food claim(long foodid, long guestid);
//
//    Food unclaim(long foodid, long guestid);
}
