package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.repository.PotluckRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "potluckService")
public class PotluckServiceImpl implements PotluckService
{
    @Autowired
    private PotluckRespository potluckrepos;

    @Override
    public List<Potluck> findAll()
    {
        List<Potluck> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        potluckrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    public Potluck findPotluckById(long id) throws EntityNotFoundException
    {
        return potluckrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Potluck id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Potluck save(Potluck potluck)
    {
        Potluck newPotluck = new Potluck();

        if (potluck.getPotluckid() != 0)
        {
            potluckrepos.findById(potluck.getPotluckid())
                .orElseThrow(() -> new EntityNotFoundException("Potluck id " + potluck.getPotluckid() + " not found!"));
            newPotluck.setPotluckid(potluck.getPotluckid());
        }

        newPotluck.setUser(potluck.getUser());
        newPotluck.setEventname(potluck.getEventname());
        newPotluck.setDate(potluck.getDate());
        newPotluck.setTime(potluck.getTime());
        newPotluck.setLocation(potluck.getLocation());
        newPotluck.setDescription(potluck.getDescription());

        newPotluck.getFoods().clear();
        for (PotluckFoods fe : potluck.getFoods())
        {
            newPotluck.getFoods()
                .add(new PotluckFoods(newPotluck,
                    fe.getFood()));
        }

//        newPotluck.getGuests().clear();
//        for (Guest ge : potluck.getGuests())
//        {
//            newPotluck.getGuests()
//                .add(new Guest(newPotluck,
//                    ge.getFname(),
//                    ge.getLname(),
//                    ge.getPrimaryemail()));
//        }

        return potluckrepos.save(newPotluck);
    }

    @Transactional
    @Override
    public Potluck update(Potluck potluck, long id)
    {
        Potluck currentPotluck = findPotluckById(id);

        //        if (helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername()))
        //        {
        if (potluck.getUser() != null)
        {
            currentPotluck.setUser(potluck.getUser());
        }

        if (potluck.getEventname() != null)
        {
            currentPotluck.setEventname(potluck.getEventname().toLowerCase());
        }

        if (potluck.getDate() != null)
        {
            currentPotluck.setDate(potluck.getDate());
        }

        if (potluck.getTime() != null)
        {
            currentPotluck.setTime(potluck.getTime());
        }

        if (potluck.getLocation() != null)
        {
            currentPotluck.setLocation(potluck.getLocation().toLowerCase());
        }

        if (potluck.getDescription() != null)
        {
            currentPotluck.setDescription(potluck.getDescription().toLowerCase());
        }

        if (potluck.getFoods().size() > 0)
        {
            currentPotluck.getFoods().clear();
            for (PotluckFoods fe : potluck.getFoods())
            {
                currentPotluck.getFoods()
                    .add(new PotluckFoods(currentPotluck,
                        fe.getFood()));
            }
        }

//        if (potluck.getGuests().size() > 0)
//        {
//            currentPotluck.getGuests().clear();
//            for (PotluckGuests ge : potluck.getGuests())
//            {
//                currentPotluck.getGuests()
//                    .add(new PotluckGuests(currentPotluck,
//                        ge.getGuest()));
//            }
//        }

        return potluckrepos.save(currentPotluck);
        //        } else
        //        {
        //            // note we should never get to this line but is needed for the compiler
        //            // to recognize that this exception can be thrown
        //            throw new ResourceNotFoundException("This user is not authorized to make change");
        //        }
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        potluckrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Potluck id " + id + " not found!"));
        potluckrepos.deleteById(id);
    }
}
