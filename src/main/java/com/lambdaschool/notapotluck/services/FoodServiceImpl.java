package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Potluck;
import com.lambdaschool.notapotluck.models.User;
import com.lambdaschool.notapotluck.repository.FoodRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
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
//        addedFood.setFoodname(newFood.getFoodname());
//        newFood = foodrepos.save(addedFood);
//    }

    // fixme add update
    @Transactional
    @Override
    public Food save(Food food)
    {
        if (food.getPotluckFoods().size() > 0)
        {
            throw new EntityNotFoundException("Potluck foods are not updated through foods.");
        }

        Food newFood = new Food();

        if (food.getFoodid() != 0)
        {
            foodrepos.findById(food.getFoodid())
                .orElseThrow(() -> new EntityNotFoundException("Food id " + food.getFoodid() + " not found!"));
            newFood.setFoodid(food.getFoodid());
        }
//        Potluck currentPotluck = potluckService.findPotluckById(food.getPotluck().getPotluckid());
//        newFood.setPotluck(currentPotluck);
        newFood.setFoodname(food.getFoodname());

        return foodrepos.save(food);
    }

    @Transactional
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

//    @Override
//    public Food claim(long foodid, long guestid)
//    {
//        if (foodrepos.findById(foodid) == null)
//        {
//            throw EntityNotFoundException
//
//        }
//
//        if (foodrepos.findById(foodid) != null)
//        {
//
//        }
//
//    }
}
