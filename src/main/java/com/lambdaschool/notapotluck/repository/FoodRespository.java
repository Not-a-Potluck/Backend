package com.lambdaschool.notapotluck.repository;

import com.lambdaschool.notapotluck.models.Food;
import org.springframework.data.repository.CrudRepository;

public interface FoodRespository extends CrudRepository<Food, Long>
{
}
