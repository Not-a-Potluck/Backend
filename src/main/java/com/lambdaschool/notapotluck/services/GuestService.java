package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Guest;

public interface GuestService
{
    Guest save(Guest guest);

    public void deleteAll();

    Guest findGuestById(long id);
}
