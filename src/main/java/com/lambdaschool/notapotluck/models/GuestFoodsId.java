package com.lambdaschool.notapotluck.models;

import java.io.Serializable;

public class GuestFoodsId implements Serializable
{
    private long guest;

    private long food;

    public GuestFoodsId()
    {
    }

    public long getGuest()
    {
        return guest;
    }

    public void setGuest(long guest)
    {
        this.guest = guest;
    }

    public long getFood()
    {
        return food;
    }

    public void setFood(long food)
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
        GuestFoodsId that = (GuestFoodsId) o;
        return guest == that.guest &&
            food == that.food;
    }

    @Override
    public int hashCode()
    {
        return 28;
    }
}
