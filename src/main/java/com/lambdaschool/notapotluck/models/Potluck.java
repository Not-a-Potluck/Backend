package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "potluck")
//@JsonIgnoreProperties(value = {"hasvalueforishost"})
public class Potluck extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long potluckid;

    private String eventname;

    private String date;

    private String time;

    private String location;

    private String description;

//    @Transient
//    public boolean hasvalueforishost = false;
//    private boolean ishost;

    /**
     Many to One relationship between potlucks and users.
     A user can have many potlucks.
     */
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    @JsonIgnoreProperties(value = "potlucks", allowSetters = true)
    private User user;

    /**
     List of food items for this potluck
     */
    @OneToMany(mappedBy = "potluck",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "potluck", allowSetters = true)
    private List<Food> foods = new ArrayList<>();

//    /**
//     * connects potluck to the food potluck combination
//     */
//    @OneToMany(mappedBy = "potluck",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "potluck", allowSetters = true)
//    private Set<PotluckFoods> foods = new HashSet<>();

    /**
     * connects potluck to the guest potluck combination
     */
    @OneToMany(mappedBy = "potluck",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "potluck", allowSetters = true)
    private Set<PotluckGuests> guests = new HashSet<>();

    public Potluck()
    {
    }

    public Potluck(
        User user,
        String eventname,
        String date,
        String time,
        String location,
        String description)
    {
        this.user = user;
        this.eventname = eventname;
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

    public String getEventname()
    {
        return eventname;
    }

    public void setEventname(String eventname)
    {
        this.eventname = eventname;
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

    public Set<PotluckGuests> getGuests()
    {
        return guests;
    }

    public void setGuests(Set<PotluckGuests> guests)
    {
        this.guests = guests;
    }
}
