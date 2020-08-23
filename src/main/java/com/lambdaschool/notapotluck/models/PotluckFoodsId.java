package com.lambdaschool.notapotluck.models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PotluckFoodsId implements Serializable
{
    private long potluck;

    private long food;

    public PotluckFoodsId()
    {
    }

    public long getPotluck()
    {
        return potluck;
    }

    public void setPotluck(long potluck)
    {
        this.potluck = potluck;
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
        PotluckFoodsId that = (PotluckFoodsId) o;
        return potluck == that.potluck &&
            food == that.food;
    }

    @Override
    public int hashCode()
    {
        return 31;
    }
}
