package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Guest;
import com.lambdaschool.notapotluck.repository.FoodRespository;
import com.lambdaschool.notapotluck.repository.GuestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "guestService")
public class GuestServiceImpl implements GuestService
{
    @Autowired
    GuestRespository guestrepos;

    @Transactional
    @Override
    public Guest save(Guest guest)
    {
        if (guest.getPotlucks().size() > 0)
        {
            throw new EntityNotFoundException("Potluck guests are not updated through guests.");
        }

        return guestrepos.save(guest);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        guestrepos.deleteAll();
    }

    @Override
    public Guest findGuestById(long id)
    {
        return guestrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Guest id " + id + " not found!"));
    }
}
