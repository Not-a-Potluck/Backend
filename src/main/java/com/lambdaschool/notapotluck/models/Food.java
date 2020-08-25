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
    @JsonIgnoreProperties("foodid")
    private long foodid;

    private String foodname;

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
        String foodname)
    {
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

    public Set<PotluckFoods> getPotlucks()
    {
        return potlucks;
    }

    public void setPotlucks(Set<PotluckFoods> potlucks)
    {
        this.potlucks = potlucks;
    }
}
