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
    GuestRespository guestRespository;

    @Transactional
    @Override
    public Guest save(Guest guest)
    {
        if (guest.getPotlucks().size() > 0)
        {
            throw new EntityNotFoundException("Potluck foods are not updated through foods.");
        }

        return guestRespository.save(guest);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        guestRespository.deleteAll();
    }
}
