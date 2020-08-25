package com.lambdaschool.notapotluck.controllers;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Potluck;
import com.lambdaschool.notapotluck.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController
{
    @Autowired
    FoodService foodService;

    // PATCH /foods/food/{foodid}/guest/{guestid}
    // verify foodid exists
    //      - if no, throw exception
    // check if guestid exists
    //      - if no, throw exception
    // check if isclaimed is true or false
    //      - if false, set to true
    //          -> for guestid, add food to isbringing
    //      - if true, return message "already claimed" fixme add exception
//    @GetMapping(value = "/foods/food/{foodid}/claim/{guestid}")
//    public ResponseEntity<?> claimFood(@PathVariable long foodid,
//                                       @PathVariable long guestid)
//    {
//        foodService.claim(foodid, guestid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    // GET /foods/food/{foodid}/unclaim/{guestid}
    // verify foodid exists
    //      - if no, throw exception
    // check if guestid exists
    //      - if no, throw exception
    // check if isclaimed is true or false
    //      - if true, set to false
    //          -> for guestid, remove food from isbringing
    //      - if false, return message "unclaimed"
//    @GetMapping(value = "/foods/food/{foodid}/unclaim/{guestid}")
//    public ResponseEntity<?> unclaimFood(@PathVariable long foodid,
//                                       @PathVariable long guestid)
//    {
//        foodService.unclaim(foodid, guestid);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
