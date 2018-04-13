/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author jakeduerr
 */
public class SuperHuman {

    private int superHumanId;

    @NotEmpty(message = "You must supply a value for Name.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String name;

    @NotEmpty(message = "You must supply a value for Description.")
    @Length(max = 150, message = "Description must be no more than 150 characters in length.")
    private String description;

    @NotEmpty(message = "You must supply a value for Powers.")
    @Length(max = 200, message = "Powers must be no more than 200 characters in length.")
    private String powers;

    private List<Organization> organizations = new ArrayList<>();

    public int getSuperHumanId() {
        return superHumanId;
    }

    public void setSuperHumanId(int superHumanId) {
        this.superHumanId = superHumanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.superHumanId;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.description);
        hash = 83 * hash + Objects.hashCode(this.powers);
        hash = 83 * hash + Objects.hashCode(this.organizations);
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
        final SuperHuman other = (SuperHuman) obj;
        if (this.superHumanId != other.superHumanId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }
    
    public boolean isMember(int orgId) {
        boolean result = false;
        if(organizations != null) {
            result = organizations.stream().anyMatch(o -> o.getOrganizationId() == orgId);
            
        }
        return result;
    }

}
