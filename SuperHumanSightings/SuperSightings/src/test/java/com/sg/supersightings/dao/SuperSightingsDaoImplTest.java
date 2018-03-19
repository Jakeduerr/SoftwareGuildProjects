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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author jakeduerr
 */
public class SuperSightingsDaoImplTest {
    
    SuperSightingsDao dao;
    
    public SuperSightingsDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        
        dao = ctx.getBean("superSightingsDao", SuperSightingsDao.class);
        
        List<Location> locals = dao.getAllLocations();
        for (Location currentLocation : locals) {
            dao.deleteLocation(currentLocation.getLocationId());
        }
        List<Sighting> sightings = dao.getAllSightings();
        for (Sighting currentSighting : sightings) {
            dao.deleteSighting(currentSighting.getSightingId());
        }
        List<SuperHuman> supers = dao.getAllSuperHumans();
        for (SuperHuman currentSuperHuman : supers) {
            dao.deleteSuperHuman(currentSuperHuman.getSuperHumanId());
        }
        List<Organization> orgs = dao.getAllOrganizations();
        for (Organization currentOrganization : orgs) {
            dao.deleteOrganization(currentOrganization.getOrganizationId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setJdbcTemplate method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testSetJdbcTemplate() {
    }

    /**
     * Test of addLocation method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setName("Empire State Building");
        location.setDescription("Fight on a skyscraper");
        location.setAddress("350 5th Ave, New York, NY 10118");
        location.setLatitude(new BigDecimal("40.7484"));
        location.setLongitude(new BigDecimal("73.9857"));
        
        dao.addLocation(location);
        
        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        
    }

    /**
     * Test of deleteLocation method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Empire State Building");
        location.setDescription("Fight on a skyscraper");
        location.setAddress("350 5th Ave, New York, NY 10118");
        location.setLatitude(new BigDecimal("40.7484"));
        location.setLongitude(new BigDecimal("73.9857"));
        
        dao.addLocation(location);
        
        Location fromDao = dao.getLocationById(location.getLocationId());
        assertEquals(fromDao, location);
        dao.deleteLocation(location.getLocationId());
        assertNull(dao.getLocationById(location.getLocationId()));
    }

    /**
     * Test of updateLocation method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testUpdateLocation() {
    }

    /**
     * Test of getLocationById method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetLocationById() {
    }

    /**
     * Test of getAllLocations method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetAllLocations() {
    }

    /**
     * Test of addSighting method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testAddSighting() {
        
    }

    /**
     * Test of deleteSighting method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testDeleteSighting() {
    }

    /**
     * Test of updateSighting method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of getSightingById method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetSightingById() {
    }

    /**
     * Test of getSightingsByLocationId method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetSightingsByLocationId() {
    }

    /**
     * Test of getSightingsBySuperHumanId method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetSightingsBySuperHumanId() {
    }

    /**
     * Test of getAllSightings method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetAllSightings() {
    }

    /**
     * Test of addSuperHuman method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testAddSuperHuman() {
        List<Organization> orgs = new ArrayList<>();
        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Group of most powerful Marvel Heros");
        organization.setPhone("763-555-5555");
        organization.setAddress("890 Fifth Avenue, Manhattan, New York City");
        
        dao.addOrganization(organization);
        orgs.add(organization);
        
        SuperHuman hero = new SuperHuman();
        hero.setOrganizations(orgs);
        hero.setName("DeadPool");
        hero.setDescription("Best Marvel hero ever!");
        hero.setPowers("Regeneration, teleportation, immortal.");
        
        dao.addSuperHuman(hero);
        
        SuperHuman fromDao = dao.getSuperHumanById(hero.getSuperHumanId());
        assertEquals(fromDao, hero);
    }

    /**
     * Test of deleteSuperHuman method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testDeleteSuperHuman() {
        List<Organization> orgs = new ArrayList<>();
        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Group of most powerful Marvel Heros");
        organization.setPhone("763-555-5555");
        organization.setAddress("890 Fifth Avenue, Manhattan, New York City");
        
        dao.addOrganization(organization);
        orgs.add(organization);
        
        SuperHuman hero = new SuperHuman();
        hero.setOrganizations(orgs);
        hero.setName("DeadPool");
        hero.setDescription("Best Marvel hero ever!");
        hero.setPowers("Regeneration, teleportation, immortal.");
        
        dao.addSuperHuman(hero);
        
        SuperHuman fromDao = dao.getSuperHumanById(hero.getSuperHumanId());
        assertEquals(fromDao, hero);
        dao.deleteSuperHuman(hero.getSuperHumanId());
        assertNull(dao.getSuperHumanById(hero.getSuperHumanId()));
    }

    /**
     * Test of updateSuperHuman method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testUpdateSuperHuman() {
    }

    /**
     * Test of getSuperHumanById method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetSuperHumanById() {
    }

    /**
     * Test of getSuperHumansByOrganizationId method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetSuperHumansByOrganizationId() {
    }

    /**
     * Test of getAllSuperHumans method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetAllSuperHumans() {
    }

    /**
     * Test of addOrganization method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testAddOrganization() {
        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Group of most powerful Marvel Heros");
        organization.setPhone("763-555-5555");
        organization.setAddress("123 Blue Bird St. Salt Lake City, Utah");
        
        dao.addOrganization(organization);
        
        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);
                
    }

    /**
     * Test of deleteOrganization method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testDeleteOrganization() {
        Organization organization = new Organization();
        organization.setName("The Avengers");
        organization.setDescription("Group of most powerful Marvel Heros");
        organization.setPhone("763-555-5555");
        organization.setAddress("123 Blue Bird St. Salt Lake City, Utah");
        
        dao.addOrganization(organization);
        
        Organization fromDao = dao.getOrganizationById(organization.getOrganizationId());
        assertEquals(fromDao, organization);
        dao.deleteOrganization(organization.getOrganizationId());
        assertNull(dao.getOrganizationById(organization.getOrganizationId()));
    }

    /**
     * Test of updateOrganization method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testUpdateOrganization() {
    }

    /**
     * Test of getOrganizationById method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetOrganizationById() {
    }

    /**
     * Test of getAllOrganizations method, of class SuperSightingsDaoImpl.
     */
    @Test
    public void testGetAllOrganizations() {
    }
    
}
