//package com.lambdaschool.notapotluck.models;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "guestfoods")
//@IdClass(GuestFoodsId.class)
//public class GuestFoods implements Serializable
//{
//    /**
//     * 1/2 of the primary key (long) for guestfoods.
//     * Also is a foreign key into the guest table
//     */
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "guestid")
//    @JsonIgnoreProperties(value = "foods", allowSetters = true)
//    private Guest guest;
//
//    /**
//     * 1/2 of the primary key (long) for guestfoods.
//     * Also is a foreign key into the foods table
//     */
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "foodid")
//    @JsonIgnoreProperties(value = "guests", allowSetters = true)
//    private Food food;
//
//    public GuestFoods()
//    {
//    }
//
//    public GuestFoods(
//        Guest guest,
//        Food food)
//    {
//        this.guest = guest;
//        this.food = food;
//    }
//
//    public Guest getGuest()
//    {
//        return guest;
//    }
//
//    public void setGuest(Guest guest)
//    {
//        this.guest = guest;
//    }
//
//    public Food getFood()
//    {
//        return food;
//    }
//
//    public void setFood(Food food)
//    {
//        this.food = food;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o)
//        {
//            return true;
//        }
////        if (o == null || getClass() != o.getClass())
//        if (!(o instanceof GuestFoods))
//        {
//            return false;
//        }
//        GuestFoods that = (GuestFoods) o;
//        return ((guest == null) ? 0 : guest.getGuestid()) == ((that.guest == null) ? 0 : that.guest.getGuestid()) &&
//            ((food == null) ? 0 : food.getFoodid()) == ((that.food == null) ? 0 : that.food.getFoodid());
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return 28;
//    }
//}
