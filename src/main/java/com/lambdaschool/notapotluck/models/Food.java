package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
@JsonIgnoreProperties(value = {"hasvalueforisclaimed"})
public class Food
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties("foodid")
    private long foodid;

    private String foodname;

    @Transient
    public boolean hasvalueforisclaimed = false;
    private boolean isclaimed;

//    @ManyToOne
//    @JoinColumn(name = "guestid", nullable = false)
//    @JsonIgnoreProperties(value = "foods", allowSetters = true)
//    private Guest guest;

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
        this.isclaimed = false;
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

    public boolean isClaimed()
    {
        return isclaimed;
    }

    public void setClaimed(boolean claimed)
    {
        hasvalueforisclaimed = true;
        isclaimed = claimed;
    }
}
