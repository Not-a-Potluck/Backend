package com.lambdaschool.notapotluck.controllers;

import com.lambdaschool.notapotluck.models.Food;
import com.lambdaschool.notapotluck.models.Guest;
import com.lambdaschool.notapotluck.services.FoodService;
import com.lambdaschool.notapotluck.services.GuestService;
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
@RequestMapping("/guests")
public class GuestController
{
    @Autowired
    GuestService guestService;

    // GET http://localhost:2019/guests/guests
    @GetMapping(value = "/guests",
        produces = "application/json")
    public ResponseEntity<?> listGuests()
    {
        List<Guest> allGuests = guestService.findAll();
        return new ResponseEntity<>(allGuests,
            HttpStatus.OK);
    }

    // POST http://localhost:2019/guests/potluck/{potluckid}/guest/{fname}/{lname}/{primaryemail}
    @PostMapping(value = "/potluck/{potluckid}/guest/{fname}/{lname}/{primaryemail}")
    public ResponseEntity<?> addNewFood(@PathVariable
                                            long potluckid,
                                        @PathVariable
                                            String fname,
                                         @PathVariable
                                                String lname,
                                                @PathVariable
                                                    String primaryemail) throws URISyntaxException
    {
        Guest newGuest = guestService.save(potluckid, fname, lname, primaryemail);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newGuestURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{guestid}")
            .buildAndExpand(newGuest.getGuestid())
            .toUri();
        responseHeaders.setLocation(newGuestURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

//    // POST http://localhost:2019/guest/guest
//    @PostMapping(value = "/guest")
//    public ResponseEntity<?> addNewGuest(@Valid
//                                        @RequestBody
//                                             Guest newguest) throws URISyntaxException
//    {
//        newguest.setGuestid(0);
//        newguest = guestService.save(newguest);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newGuestURI = ServletUriComponentsBuilder.fromCurrentRequest()
//            .path("/{guestid}")
//            .buildAndExpand(newguest.getGuestid())
//            .toUri();
//        responseHeaders.setLocation(newGuestURI);
//
//        return new ResponseEntity<>(null,
//            responseHeaders,
//            HttpStatus.CREATED);
//    }
}
