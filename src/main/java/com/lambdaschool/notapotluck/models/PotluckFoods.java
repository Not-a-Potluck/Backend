package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "potluckfoods")
public class PotluckFoods implements Serializable
{
    /**
     * 1/2 of the primary key (long) for potluckfoods.
     * Also is a foreign key into the potluck table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "potluckid")
    @JsonIgnoreProperties(value = "foods", allowSetters = true)
    private Potluck potluck;

    /**
     * 1/2 of the primary key (long) for potluckfoods.
     * Also is a foreign key into the foods table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "foodid")
    @JsonIgnoreProperties(value = "potlucks", allowSetters = true)
    private Food food;

    public PotluckFoods()
    {
    }

    public PotluckFoods(
        Potluck potluck,
        Food food)
    {
        this.potluck = potluck;
        this.food = food;
    }

    public Potluck getPotluck()
    {
        return potluck;
    }

    public void setPotluck(Potluck potluck)
    {
        this.potluck = potluck;
    }

    public Food getFood()
    {
        return food;
    }

    public void setFood(Food food)
    {
        this.food = food;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        PotluckFoods that = (PotluckFoods) o;
        return ((potluck == null) ? 0 : potluck.getPotluckid()) == ((that.potluck == null) ? 0 : that.potluck.getPotluckid()) &&
            ((food == null) ? 0 : food.getFoodid()) == ((that.food == null) ? 0 : that.food.getFoodid());
    }

    @Override
    public int hashCode()
    {
        return 31;
    }
}
