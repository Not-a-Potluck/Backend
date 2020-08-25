package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;

public interface FoodService
{
    Food save(Food food);

    public void deleteAll();

    Food claim(long foodid, long guestid);

    Food unclaim(long foodid, long guestid);
}
