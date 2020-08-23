package com.lambdaschool.notapotluck.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity // allows interaction with user table
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // never gets displayed
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String primaryemail;

    private String imageurl;

    /**
     * A list of potlucks for this user
     */
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Potluck> potlucks = new ArrayList<>();

    public User()
    {
    }

    public User(
        String username,
        String password,
        String primaryemail,
        String imageurl)
    {
        this.username = username;
        this.password = password;
        this.primaryemail = primaryemail;
        this.imageurl = imageurl;
    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPrimaryemail()
    {
        return primaryemail;
    }

    public void setPrimaryemail(String primaryemail)
    {
        this.primaryemail = primaryemail;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public void setImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }

    public List<Potluck> getPotlucks()
    {
        return potlucks;
    }

    public void setPotlucks(List<Potluck> potlucks)
    {
        this.potlucks = potlucks;
    }
}
