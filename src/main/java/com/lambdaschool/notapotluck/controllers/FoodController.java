package com.lambdaschool.notapotluck.controllers;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Potluck;
import com.lambdaschool.notapotluck.services.FoodService;
import com.lambdaschool.notapotluck.services.PotluckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController
{
    @Autowired
    FoodService foodService;

    @Autowired
    PotluckService potluckService;

    // GET http://localhost:2019/foods/foods
    @GetMapping(value = "/foods",
        produces = "application/json")
    public ResponseEntity<?> listFoods()
    {
        List<Food> allFoods = foodService.findAll();
        return new ResponseEntity<>(allFoods,
            HttpStatus.OK);
    }

    // POST http://localhost:2019/foods/potluck/{potluckid}/food/{newfood}
    @PostMapping(value = "/potluck/{potluckid}/food/{newfood}")
    public ResponseEntity<?> addNewFood(@PathVariable
                                                long potluckid,
                                        @PathVariable
                                            String newfood) throws URISyntaxException
    {
        Food newFood = foodService.save(potluckid, newfood);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newFoodURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{foodid}")
            .buildAndExpand(newFood.getFoodid())
            .toUri();
        responseHeaders.setLocation(newFoodURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    // DELETE http://localhost:2019/foods/food/{foodid}
    @DeleteMapping(value = "/food/{foodid}")
    public ResponseEntity<?> deleteFoodById(
        @PathVariable
            long id)
    {
        foodService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
