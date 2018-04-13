/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jakeduerr
 */
public class Sighting {

    
    private int sightingId;
    
    private Date date;
    
    private Location location;
    
    private List<SuperHuman> superHumans = new ArrayList<>();

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<SuperHuman> getSuperHumans() {
        return superHumans;
    }

    public void setSuperHumans(List<SuperHuman> superHumans) {
        this.superHumans = superHumans;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.sightingId;
        hash = 89 * hash + Objects.hashCode(this.date);
        hash = 89 * hash + Objects.hashCode(this.location);
        hash = 89 * hash + Objects.hashCode(this.superHumans);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.superHumans, other.superHumans)) {
            return false;
        }
        return true;
    }
    
    public boolean isMember(int superhumanId) {
        boolean result = false;
        if(superHumans != null) {
            result = superHumans.stream().anyMatch(o -> o.getSuperHumanId() == superhumanId);
            
        }
        return result;
    }

}
