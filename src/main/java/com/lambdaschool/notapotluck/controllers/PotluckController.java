package com.lambdaschool.notapotluck.controllers;

import com.lambdaschool.notapotluck.models.Potluck;
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
@RequestMapping("/potlucks")
public class PotluckController
{
    @Autowired
    PotluckService potluckService;

    // GET http://localhost:2019/potlucks/potlucks
    @GetMapping(value = "/potlucks", produces = "application/json")
    public ResponseEntity<?> listAllPotlucks()
    {
        List<Potluck> myPotlucks = potluckService.findAll();
        return new ResponseEntity<>(myPotlucks, HttpStatus.OK);
    }

    // POST http://localhost:2019/potlucks/potluck
    @PostMapping(value = "/potluck")
    public ResponseEntity<?> addNewPotluck(@Valid
                                           @RequestBody
                                                   Potluck newpotluck) throws URISyntaxException
    {
        newpotluck.setPotluckid(0);
        newpotluck = potluckService.save(newpotluck);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPotluckURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{potluckid}")
            .buildAndExpand(newpotluck.getPotluckid())
            .toUri();
        responseHeaders.setLocation(newPotluckURI);

        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

    // PUT http://localhost:2019/potlucks/potluck/{potluckid}
    @PutMapping(value = "/potluck/{potluckid}", consumes = "application/json")
    public ResponseEntity<?> updateFullPotluck(@Valid @RequestBody Potluck updatePotluck,
                                            @PathVariable long potluckid)
    {
        updatePotluck.setPotluckid(potluckid);
        potluckService.save(updatePotluck);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // DELETE http://localhost:2019/potlucks/potluck/{potluckid}
    @DeleteMapping(value = "/potluck/{id}")
    public ResponseEntity<?> deleteUserById(
        @PathVariable
            long id)
    {
        potluckService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
