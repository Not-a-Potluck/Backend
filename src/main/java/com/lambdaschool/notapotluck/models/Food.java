package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long foodid;

    private String foodname;

    /**
     * Many to One relationship between foods and potlucks.
     * A potluck can have many foods.
     */
    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "foods", allowSetters = true)
    private Potluck potluck;

    /**
     Many to One relationship between potlucks and users.
     A guest can have many foods.
     */
    @ManyToOne
    @JoinColumn(name = "guestid", nullable = false)
    @JsonIgnoreProperties(value = "foods", allowSetters = true)
    private Guest guest;

    /**
     * connects food to the potluck food combination
     */
    @OneToMany(mappedBy = "food",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "food", allowSetters = true)
    private Set<PotluckFoods> potlucks = new HashSet<>();

    public Food()
    {
    }

    public Food(
        Potluck potluck,
        Guest guest,
        String foodname)
    {
        this.potluck = potluck;
        this.guest = guest;
        this.foodname = foodname;
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

    public Potluck getPotluck()
    {
        return potluck;
    }

    public void setPotluck(Potluck potluck)
    {
        this.potluck = potluck;
    }

    public Guest getGuest()
    {
        return guest;
    }

    public void setGuest(Guest guest)
    {
        this.guest = guest;
    }

    public Set<PotluckFoods> getPotlucks()
    {
        return potlucks;
    }

    public void setPotlucks(Set<PotluckFoods> potlucks)
    {
        this.potlucks = potlucks;
    }
}
