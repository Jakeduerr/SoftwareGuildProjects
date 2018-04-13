/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.SuperSightingsDao;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperHuman;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author jakeduerr
 */
@Component
public class SuperSightingsServiceLayerImpl implements SuperSightingsServiceLayer {
    
    @Inject
    SuperSightingsDao dao;
    
    

    @Override
    public List<Sighting> findSightingsForLocation(Location location) {
        return dao.findSightingsForLocation(location);
    }

    @Override
    public List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
        return dao.findSightingsForSuperHuman(superHuman);
    }
    
    @Override
    public List<Sighting> findTenMostRecentSightings() {
        return dao.findTenMostRecentSightings();
    }
    
    @Override
    public List<SuperHuman> findSuperHumansForSighting(Sighting sighting) {
        return dao.findSuperHumansForSighting(sighting);
    }

    @Override
    public List<SuperHuman> findSuperHumansForOrganization(Organization organization) {
        return dao.findSuperHumansForOrganization(organization);
    }
    
    @Override
    public void addLocation(Location location) {
        dao.addLocation(location);
    }

    @Override
    public void deleteLocation(int locationId) {
        dao.deleteLocation(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        dao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) {
        return dao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public void addSighting(Sighting sighting) {
        dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        dao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        dao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        return dao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        return dao.getSightingsByLocationId(locationId);
    }

    @Override
    public List<Sighting> getSightingsBySuperHumanId(int superHumanId) {
        return dao.getSightingsBySuperHumanId(superHumanId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public void addSuperHuman(SuperHuman superHuman) {
        dao.addSuperHuman(superHuman);
    }

    @Override
    public void deleteSuperHuman(int superHumanId) {
        dao.deleteSuperHuman(superHumanId);
    }

    @Override
    public void updateSuperHuman(SuperHuman superHuman) {
        dao.updateSuperHuman(superHuman);
    }

    @Override
    public SuperHuman getSuperHumanById(int superHumanId) {
        return dao.getSuperHumanById(superHumanId);
    }

    @Override
    public List<SuperHuman> getSuperHumansByOrganizationId(int organizationId) {
        return dao.getSuperHumansByOrganizationId(organizationId);
    }

    @Override
    public List<SuperHuman> getAllSuperHumans() {
        return dao.getAllSuperHumans();
    }

    @Override
    public void addOrganization(Organization organization) {
        dao.addOrganization(organization);
    }

    @Override
    public void deleteOrganization(int organizationId) {
        dao.deleteOrganization(organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        dao.updateOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        return dao.getOrganizationById(organizationId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    
    
}
