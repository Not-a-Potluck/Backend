package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Guest;

import java.util.List;

public interface GuestService
{
//    Guest save(Guest guest);

    public void deleteAll();

    Guest findGuestById(long id);

    List<Guest> findAll();

    Guest save(
        long potluckid,
        String fname,
        String lname,
        String primaryemail);
}
