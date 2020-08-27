//package com.lambdaschool.notapotluck.models;
//
//import java.io.Serializable;
//
//public class PotluckGuestsId implements Serializable
//{
//    private long potluck;
//
//    private long guest;
//
//    public PotluckGuestsId()
//    {
//    }
//
//    public long getPotluck()
//    {
//        return potluck;
//    }
//
//    public void setPotluck(long potluck)
//    {
//        this.potluck = potluck;
//    }
//
//    public long getGuest()
//    {
//        return guest;
//    }
//
//    public void setGuest(long guest)
//    {
//        this.guest = guest;
//    }
//
//    @Override
//    public boolean equals(Object o)
//    {
//        if (this == o)
//        {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass())
//        {
//            return false;
//        }
//        PotluckGuestsId that = (PotluckGuestsId) o;
//        return potluck == that.potluck &&
//            guest == that.guest;
//    }
//
//    @Override
//    public int hashCode()
//    {
//        return 37;
//    }
//}
