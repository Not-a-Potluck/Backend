package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "potluck")
public class Potluck
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long potluckid;

    private String date;

    private String time;

    private String location;

    private String description;

    /**
     Many to One relationship between potlucks and users.
     A user can have many potlucks.
     */
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "potlucks", allowSetters = true)
    private User user;

    /**
     * A list of foods for this potluck
     */
    @OneToMany(mappedBy = "potluck",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "potluck", allowSetters = true)
    private List<Food> foods = new ArrayList<>();

    /**
     * A list of guests for this potluck
     */
    @OneToMany(mappedBy = "potluck",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "potluck", allowSetters = true)
    private List<Guest> guests = new ArrayList<>();

    public Potluck()
    {
    }

    public Potluck(
        String date,
        String time,
        String location,
        String description)
    {
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public long getPotluckid()
    {
        return potluckid;
    }

    public void setPotluckid(long potluckid)
    {
        this.potluckid = potluckid;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Food> getFoods()
    {
        return foods;
    }

    public void setFoods(List<Food> foods)
    {
        this.foods = foods;
    }
}
