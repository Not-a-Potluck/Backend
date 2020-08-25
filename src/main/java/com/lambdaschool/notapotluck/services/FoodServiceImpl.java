package com.lambdaschool.notapotluck.services;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.repository.FoodRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "foodService")
public class FoodServiceImpl implements FoodService
{
    @Autowired
    FoodRespository foodrepos;

    @Transactional
    @Override
    public Food save(Food food)
    {
        if (food.getPotlucks().size() > 0)
        {
            throw new EntityNotFoundException("Potluck foods are not updated through foods.");
        }

        return foodrepos.save(food);
    }

    @Transactional
    @Override
    public void deleteAll()
    {
        foodrepos.deleteAll();
    }
}
