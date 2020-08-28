package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "guests")
@JsonIgnoreProperties(value = {"hasvalueforresponded","hasvalueforattending"})
public class Guest extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @JsonIgnoreProperties("guestid")
    private long guestid;

    private String fname;

    private String lname;

    private String primaryemail;

    @Transient
    public boolean hasvalueforresponded = false;
    private boolean responded;

    @Transient
    public boolean hasvalueforattending = false;
    private boolean attending;

//    /**
//     * connects guest to the potluck guest combination
//     */
//    @OneToMany(mappedBy = "guest",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "guest", allowSetters = true)
//    private Set<PotluckGuests> potlucks = new HashSet<>();

//    @OneToMany(mappedBy = "guest",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "guest", allowSetters = true)
//    private List<Food> foods = new ArrayList<>();

//    /**
//     * connects guest to the guest food combination
//     */
//    @OneToMany(mappedBy = "guest",
//        cascade = CascadeType.ALL,
//        orphanRemoval = true)
//    @JsonIgnoreProperties(value = "guest", allowSetters = true)
//    private Set<GuestFoods> isbringing = new HashSet<>();

    /**
     potluck that this guest is associated with
     */
    @ManyToOne
    @JoinColumn(name = "potluckid", nullable = false)
    @JsonIgnoreProperties(value = "guest", allowSetters = true)
    private Potluck potluck;

    public Guest()
    {
    }

    public Guest(
        Potluck potluck,
        String fname,
        String lname,
        String primaryemail)
    {
        this.potluck = potluck;
        this.fname = fname;
        this.lname = lname;
        this.primaryemail = primaryemail;
        this.responded = false;
        this.attending = false;
    }

    public long getGuestid()
    {
        return guestid;
    }

    public void setGuestid(long guestid)
    {
        this.guestid = guestid;
    }

    public String getFname()
    {
        return fname;
    }

    public void setFname(String fname)
    {
        this.fname = fname;
    }

    public String getLname()
    {
        return lname;
    }

    public void setLname(String lname)
    {
        this.lname = lname;
    }

    public String getPrimaryemail()
    {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail;
    }

    public boolean isResponded()
    {
        return responded;
    }

    public void setResponded(boolean responded)
    {
        hasvalueforresponded = true;
        this.responded = responded;
    }

    public boolean isAttending()
    {
        return attending;
    }

    public void setAttending(boolean attending)
    {
        hasvalueforattending = true;
        this.attending = attending;
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
