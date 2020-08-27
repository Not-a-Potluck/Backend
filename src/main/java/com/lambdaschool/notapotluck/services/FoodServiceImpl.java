package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Potluck;
import com.lambdaschool.notapotluck.repository.FoodRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "foodService")
public class FoodServiceImpl implements FoodService
{
    @Autowired
    FoodRespository foodrepos;

    @Autowired
    PotluckService potluckService;

//    @Override
//    public void addFood(Food newFood, Potluck potluck) {
//        long potluckid = newFood.getFoodid();
//        Food addedFood = new Food();
//        addedFood.setPotluck(potluck);
//        addedFood.setFoodid(0);
//        addedFood.setFoodname(newFood.getFoodname());
//        newFood = foodrepos.save(addedFood);
//    }

//    // fixme add update
//    @Override
//    public Food save(long potluckid, Food food)
//    {
//        return null;
//    }

    @Override
    public void deleteAll()
    {
        foodrepos.deleteAll();
    }

    @Override
    public Food findFoodById(long id)
    {
        return foodrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Food id " + id + " not found!"));
    }

    @Override
    public List<Food> findAll()
    {
        List<Food> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        foodrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public Food save(
        long potluckid,
        String foodname)
    {
        Potluck currentPotluck = potluckService.findPotluckById(potluckid);

        Food newFood = new Food(currentPotluck, foodname);
        return foodrepos.save(newFood);
    }
}
