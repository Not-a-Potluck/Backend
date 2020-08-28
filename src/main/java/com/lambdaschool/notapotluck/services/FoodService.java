package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;

import java.util.List;

public interface FoodService
{
    public void deleteAll();

    Food findFoodById(long id);

    List<Food> findAll();

    Food save(
        long potluckid,
        String foodname);

    void delete(long id);

//    Food claim(long foodid, long guestid);
//
//    Food unclaim(long foodid, long guestid);
}
