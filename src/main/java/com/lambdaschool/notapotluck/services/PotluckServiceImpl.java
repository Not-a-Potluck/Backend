package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.*;
import com.lambdaschool.notapotluck.repository.PotluckRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service(value = "potluckService")
public class PotluckServiceImpl implements PotluckService
{
    @Autowired
    private PotluckRespository potluckrepos;

    @Autowired
    private UserService userService;

    @Autowired
    private FoodService foodService;

    @Autowired
    private GuestService guestService;

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

//    @Transactional
//    @Override
//    public Potluck save(Potluck potluck)
//    {
//        Potluck newPotluck = new Potluck();
//        if (potluck.getPotluckid() != 0)
//        {
//            potluckrepos.findById(potluck.getPotluckid())
//                .orElseThrow(() -> new EntityNotFoundException("Potluck id " + potluck.getPotluckid() + " not found!"));
//            newPotluck.setPotluckid(potluck.getPotluckid());
//        }
//        newPotluck.setUser(potluck.getUser());
//        newPotluck.setEventname(potluck.getEventname());
//        newPotluck.setDate(potluck.getDate());
//        newPotluck.setTime(potluck.getTime());
//        newPotluck.setLocation(potluck.getLocation());
//        newPotluck.setDescription(potluck.getDescription());
//
//        newPotluck.getGuests().clear();
//        for (PotluckGuests ge : potluck.getGuests())
//        {
//            newPotluck.getGuests()
//                .add(new PotluckGuests(newPotluck,
//                    ge.getGuest()));
//        }
//        newPotluck.getFoods().clear();
//        for (PotluckFoods fe : potluck.getFoods())
//        {
//            PotluckGuests foodBringingGuest = new PotluckGuests();
//            foodBringingGuest = null;
//            for (PotluckGuests plg : newPotluck.getGuests())
//            {
//                if (plg.equals(fe.getPotluckguest()))
//                    foodBringingGuest = plg;
//            }
//
//            newPotluck.getFoods()
//                .add(new PotluckFoods(newPotluck,
//                    fe.getFood(), foodBringingGuest));
//            ;
//        }
//        return potluckrepos.save(newPotluck);
//    }
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
        User currentUser = userService.findUserById(potluck.getUser().getUserid());
        newPotluck.setUser(currentUser);
        newPotluck.setEventname(potluck.getEventname());
        newPotluck.setDate(potluck.getDate());
        newPotluck.setTime(potluck.getTime());
        newPotluck.setLocation(potluck.getLocation());
        newPotluck.setDescription(potluck.getDescription());

        newPotluck.getFoods().clear();
        for (PotluckFoods fe : potluck.getFoods())
        {
            Food addFood = foodService.findFoodById(fe.getFood().getFoodid());
            newPotluck.getFoods()
                .add(new PotluckFoods(newPotluck,
                    addFood));
        }

        newPotluck.getGuests().clear();
        for (PotluckGuests ge : potluck.getGuests())
        {
            Guest addGuest = guestService.findGuestById(ge.getGuest().getGuestid());
            newPotluck.getGuests()
                .add(new PotluckGuests(newPotluck,
                    addGuest));
        }

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
                Food addFood = foodService.findFoodById(fe.getFood().getFoodid());

                currentPotluck.getFoods()
                    .add(new PotluckFoods(currentPotluck,
                        addFood));
            }
        }

        if (potluck.getGuests().size() > 0)
        {
            currentPotluck.getGuests().clear();
            for (PotluckGuests ge : potluck.getGuests())
            {
                currentPotluck.getGuests()
                    .add(new PotluckGuests(currentPotluck,
                        ge.getGuest()));
            }
        }

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

    @Transactional
    @Override
    public void deleteAll()
    {
        potluckrepos.deleteAll();
    }
}
