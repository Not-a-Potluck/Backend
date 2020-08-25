package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "potluckguests")
@IdClass(PotluckGuestsId.class)
public class PotluckGuests implements Serializable
{
    /**
     * 1/2 of the primary key (long) for potluckfoods.
     * Also is a foreign key into the potluck table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "potluckid")
    @JsonIgnoreProperties(value = "guests", allowSetters = true)
    private Potluck potluck;

    /**
     * 1/2 of the primary key (long) for potluckfoods.
     * Also is a foreign key into the foods table
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "guestid")
    @JsonIgnoreProperties(value = "potlucks", allowSetters = true)
    private Guest guest;

    public PotluckGuests()
    {
    }

    public PotluckGuests(
        Potluck potluck,
        Guest guest)
    {
        this.potluck = potluck;
        this.guest = guest;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
//        if (o == null || getClass() != o.getClass())
        if (!(o instanceof PotluckGuests))
        {
            return false;
        }
        PotluckGuests that = (PotluckGuests) o;
        return ((potluck == null) ? 0 : potluck.getPotluckid()) == ((that.potluck == null) ? 0 : that.potluck.getPotluckid()) &&
            ((guest == null) ? 0 : guest.getGuestid()) == ((that.guest == null) ? 0 : that.guest.getGuestid());
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
