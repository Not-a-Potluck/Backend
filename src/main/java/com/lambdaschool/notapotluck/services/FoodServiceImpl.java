package com.lambdaschool.notapotluck.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "foodService")
public class FoodServiceImpl implements FoodService
{
}
