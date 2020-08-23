package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods")
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodid;

    private String foodname;

    private String description;

    private String imageurl;

    /**
     * Many to One relationship between foods and potlucks.
     * A potluck can have many foods.
     */
    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "foods", allowSetters = true)
    private Potluck potluck;

    public Food()
    {
    }

    public Food(
        String foodname,
        String description,
        String imageurl)
    {
        this.foodname = foodname;
        this.description = description;
        this.imageurl = imageurl;
    }

    public long getFoodid()
    {
        return foodid;
    }

    public void setFoodid(long foodid)
    {
        this.foodid = foodid;
    }

    public String getFoodname()
    {
        return foodname;
    }

    public void setFoodname(String foodname)
    {
        this.foodname = foodname;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public void setImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }

    public Potluck getPotluck()
    {
        return potluck;
    }

    public void setPotluck(Potluck potluck)
    {
        this.potluck = potluck;
    }
}
