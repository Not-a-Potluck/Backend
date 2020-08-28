package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "foods")
@JsonIgnoreProperties(value = {"hasvalueforisclaimed"})
public class Food extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonIgnoreProperties("foodid")
    private long foodid;

    private String foodname;

//    @Transient
//    public boolean hasvalueforisclaimed = false;
//    private boolean isclaimed;

    /**
     potluck that this food is associated with
     */
    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "food", allowSetters = true)
    private Potluck potluck;

//    /**
//     * connects food to the potluck food combination
//     */
//    @OneToMany(mappedBy = "food",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "food", allowSetters = true)
//    private Set<PotluckFoods> potluckFoods = new HashSet<>();

//    @OneToMany(mappedBy = "food",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "isbringing", allowSetters = true)
//    private Set<GuestFoods> guest = new HashSet<>();

    public Food()
    {
    }

    public Food(
        Potluck potluck,
        String foodname)
    {
        this.potluck = potluck;
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
}
