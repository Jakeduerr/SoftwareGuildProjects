/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperHuman;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SuperSightingsDao {
    
    //Location methods
    void addLocation(Location location);
    
    void deleteLocation(int locationId);
    
    void updateLocation(Location location);
    
    Location getLocationById(int locationId);
    
    List<Location> getAllLocations();
    
    //Sighting methods
    void addSighting(Sighting sighting);
    
    void deleteSighting(int sightingId);
    
    void updateSighting(Sighting sighting);
    
    Sighting getSightingById(int sightingId);
    
    List<Sighting> getSightingsByLocationId(int locationId);
    
    List<Sighting> getSightingsBySuperHumanId(int superHumanId);
    
    List<Sighting> getAllSightings();
    
    //SuperHuman methods
    void addSuperHuman(SuperHuman superHuman);
    
    void deleteSuperHuman(int superHumanId);
    
    void updateSuperHuman(SuperHuman superHuman);
    
    SuperHuman getSuperHumanById(int superHumanId);
    
    List<SuperHuman> getSuperHumansByOrganizationId(int organizationId);
    
    List<SuperHuman> getAllSuperHumans();
    
    //Organization methods
    void addOrganization(Organization organization);
    
    void deleteOrganization(int organizationId);
    
    void updateOrganization(Organization organization);
    
    Organization getOrganizationById(int organizationId);
    
    List<Organization> getAllOrganizations();
    
}
