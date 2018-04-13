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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jakeduerr
 */
public class SuperSightingsDaoImpl implements SuperSightingsDao {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //location
    private static final String SQL_INSERT_LOCATION
            = "insert into location (`name`, description, address, "
            + "latitude, longitude) values (?, ?, ?, ?, ?)";

    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";

    private static final String SQL_UPDATE_LOCATION
            = "update location set `name` = ?, description = ?, address = ?, "
            + "latitude = ?, longitude = ? where locationId = ?";

    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";

    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select lo.locationId, lo.name, lo.description, lo.address, "
            + "lo.latitude, lo.longitude from location lo "
            + "join sighting s on lo.locationId = s.locationId "
            + "where s.sightingId = ?";

    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";

    //sighting
    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (locationId, `date`) values (?, ?)";

    private static final String SQL_INSERT_SUPERHUMAN_SIGHTING
            = "insert into superHumanSighting (sightingId, superHumanId) values (?, ?)";

    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightingId = ?";

    //make query for delete sighting thats referencing a location
    private static final String SQL_DELETE_SIGHTING_LOCATION
            = "delete from sighting where locationId = ?";

    private static final String SQL_DELETE_SUPERHUMAN_SIGHTING
            = "delete from superHumanSighting where sightingId = ?";

    //work in progress
    private static final String SQL_DELETE_SUPERHUMAN_SIGHTING_LOCATION
            = "delete shs from superHumanSighting shs "
            + "join sighting s on shs.sightingId = s.sightingId where s.locationId = ?";

    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set locationId = ?, `date` = ? where sightingId = ?";

    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sightingId = ?";

    private static final String SQL_SELECT_SIGHTING_BY_LOCATION_ID
            = "select * from sighting where locationId = ?";

    private static final String SQL_SELECT_SIGHTING_BY_SUPERHUMAN_ID
            = "select s.sightingId, s.locationId, s.date from sighting s "
            + "join superHumanSighting shs on s.sightingId = shs.sightingId "
            + "where shs.superHumanId = ?";
    
    private static final String SQL_SELECT_SIGHTING_MOST_RECENT
            = "select * from sighting order by date desc limit 0,10";

    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";

    //superhuman
    private static final String SQL_INSERT_SUPERHUMAN
            = "insert into superHuman (`name`, description, powers) values (?, ?, ?)";

    private static final String SQL_INSERT_SUPERHUMAN_ORGANIZATION
            = "insert into superHumanOrganization (superHumanId, organizationId) values (?, ?)";

    private static final String SQL_DELETE_SUPERHUMAN
            = "delete from superHuman where superHumanId = ?";
    
    //make superhuman delete from sighting/superhuman by superhuman Id
    private static final String SQL_DELETE_SIGHTING_SUPERHUMAN
            = "delete from superHumanSighting where superHumanId = ?";
//            = "delete shs from superHumanSighting shs "
//            + "join superHuman sh on shs.sightingId = sh.sightingId where sh.superHumanId = ?";

    private static final String SQL_DELETE_SUPERHUMAN_ORGANIZATION
            = "delete from superHumanOrganization where superHumanId = ?";

    private static final String SQL_UPDATE_SUPERHUMAN
            = "update superHuman set `name` = ?, description = ?, powers = ? "
            + "where superHumanId = ?";

    private static final String SQL_SELECT_SUPERHUMAN
            = "select * from superHuman where superHumanId = ?";

    private static final String SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID
            = "select sh.superHumanId, sh.name, sh.description, sh.powers "
            + "from superHuman sh join superHumanSighting shs on sh.superHumanId = "
            + "shs.superHumanId where shs.sightingId = ?";

    private static final String SQL_SELECT_SUPERHUMAN_BY_ORGANIZATION_ID
            = "select sh.superHumanId, sh.name, sh.description, sh.powers "
            + "from superHuman sh join superHumanOrganization sho on sh.superHumanId = "
            + "sho.superHumanId where sho.organizationId = ?";

    private static final String SQL_SELECT_ALL_SUPERHUMANS
            = "select * from superHuman";

    //organization
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (`name`, description, phone, address) "
            + "values (?, ?, ?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationId = ?";

    private static final String SQL_DELETE_ORGANIZATION_SUPERHUMAN
            = "delete from superHumanOrganization where organizationId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set `name` = ?, description = ?, phone = ?, address = ? "
            + "where organizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION_BY_SUPERHUMAN_ID
            = "select org.organizationId, org.name, org.description, org.phone, "
            + "org.address from organization org join superHumanOrganization sho "
            + "on org.organizationId = sho.organizationId where sho.superHumanId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";

    private void insertSuperHumansSightings(Sighting sighting) {
        final int sightingId = sighting.getSightingId();
        final List<SuperHuman> supers = sighting.getSuperHumans();
        for (SuperHuman currentSuper : supers) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_SIGHTING,
                    sightingId, currentSuper.getSuperHumanId());
        }
    }

    private void insertOrganizationsSuperHumans(SuperHuman superHuman) {
        final int superHumanId = superHuman.getSuperHumanId();
        final List<Organization> orgs = superHuman.getOrganizations();
        for (Organization currentOrg : orgs) {
            jdbcTemplate.update(SQL_INSERT_SUPERHUMAN_ORGANIZATION,
                    superHumanId, currentOrg.getOrganizationId());
        }
    }

    private Location findLocationForSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID,
                new LocationMapper(),
                sighting.getSightingId());
    }

    //public and expanded   -set superHumans and set locations for sightings
    @Override
    public List<Sighting> findSightingsForLocation(Location location) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_LOCATION_ID,
                new SightingMapper(),
                location.getLocationId());
        associateLocationAndSuperHumansWithSightings(sightings);
        return sightings;

    }

    //public and expanded
    @Override
    public List<Sighting> findSightingsForSuperHuman(SuperHuman superHuman) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_SUPERHUMAN_ID,
                new SightingMapper(),
                superHuman.getSuperHumanId());
        associateLocationAndSuperHumansWithSightings(sightings);
        return sightings;
    }
    
    @Override
    public List<Sighting> findTenMostRecentSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_SIGHTING_MOST_RECENT,
                new SightingMapper());
        return associateLocationAndSuperHumansWithSightings(sightings);
    }
    
    @Override
    public List<SuperHuman> findSuperHumansForSighting(Sighting sighting) {
        List<SuperHuman> supers = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_SIGHTING_ID,
                new SuperHumanMapper(),
                sighting.getSightingId());
        for (SuperHuman currentSuper : supers) {
            currentSuper.setOrganizations(findOrganizationForSuperHuman(currentSuper));
        }
        return supers;

    }

    //public
    @Override
    public List<SuperHuman> findSuperHumansForOrganization(Organization organization) {
        return jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_ORGANIZATION_ID,
                new SuperHumanMapper(),
                organization.getOrganizationId());
    }

    private List<Organization> findOrganizationForSuperHuman(SuperHuman superHuman) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATION_BY_SUPERHUMAN_ID,
                new OrganizationMapper(),
                superHuman.getSuperHumanId());
    }

    private List<Sighting> associateLocationAndSuperHumansWithSightings(List<Sighting> sightings) {
        for (Sighting currentSighting : sightings) {
            currentSighting.setSuperHumans(findSuperHumansForSighting(currentSighting));
            currentSighting.setLocation(findLocationForSighting(currentSighting));
        }
        return sightings;
    }

    private List<SuperHuman> associateOrganizationsWithSuperHumans(List<SuperHuman> supers) {
        for (SuperHuman currentSuper : supers) {
            currentSuper.setOrganizations(findOrganizationForSuperHuman(currentSuper));
        }
        return supers;
    }

    @Override
    @Transactional
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        location.setLocationId(locationId);
    }

    @Override
    @Transactional
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_SIGHTING_LOCATION, locationId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_LOCATION, locationId);
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }

    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    public Location getLocationById(int locationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, new LocationMapper(),
                    locationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate());

        sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
        insertSuperHumansSightings(sighting);
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_SIGHTING, sightingId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, sightingId);
    }

    @Override
    @Transactional
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationId(),
                sighting.getDate(),
                sighting.getSightingId());

        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_SIGHTING, sighting.getSightingId());

        insertSuperHumansSightings(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        try {
            Sighting sighting = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING,
                    new SightingMapper(),
                    sightingId);
            sighting.setSuperHumans(findSuperHumansForSighting(sighting));
            sighting.setLocation(findLocationForSighting(sighting));
            return sighting;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        List<Sighting> sightings
                = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_LOCATION_ID,
                        new SightingMapper(),
                        locationId);
        return associateLocationAndSuperHumansWithSightings(sightings);
    }

    @Override
    public List<Sighting> getSightingsBySuperHumanId(int superHumanId) {
        List<Sighting> sightings
                = jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_SUPERHUMAN_ID,
                        new SightingMapper(),
                        superHumanId);
        return associateLocationAndSuperHumansWithSightings(sightings);
    }

    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightings = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS,
                new SightingMapper());
        return associateLocationAndSuperHumansWithSightings(sightings);
    }

    @Override
    @Transactional
    public void addSuperHuman(SuperHuman superHuman) {
        jdbcTemplate.update(SQL_INSERT_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.getPowers());

        superHuman.setSuperHumanId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));

        insertOrganizationsSuperHumans(superHuman);
    }

    @Override
    @Transactional
    public void deleteSuperHuman(int superHumanId) {
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_ORGANIZATION, superHumanId);
        jdbcTemplate.update(SQL_DELETE_SIGHTING_SUPERHUMAN, superHumanId);
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN, superHumanId);
    }

    @Override
    @Transactional
    public void updateSuperHuman(SuperHuman superHuman) {
        jdbcTemplate.update(SQL_UPDATE_SUPERHUMAN,
                superHuman.getName(),
                superHuman.getDescription(),
                superHuman.getPowers(),
                superHuman.getSuperHumanId());
        jdbcTemplate.update(SQL_DELETE_SUPERHUMAN_ORGANIZATION, superHuman.getSuperHumanId());
        insertOrganizationsSuperHumans(superHuman);
    }

    @Override
    public SuperHuman getSuperHumanById(int superHumanId) {
        try {
            SuperHuman superHuman = jdbcTemplate.queryForObject(SQL_SELECT_SUPERHUMAN,
                    new SuperHumanMapper(),
                    superHumanId);
            superHuman.setOrganizations(findOrganizationForSuperHuman(superHuman));
            return superHuman;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<SuperHuman> getSuperHumansByOrganizationId(int organizationId) {
        List<SuperHuman> supers
                = jdbcTemplate.query(SQL_SELECT_SUPERHUMAN_BY_ORGANIZATION_ID,
                        new SuperHumanMapper(),
                        organizationId);
        return associateOrganizationsWithSuperHumans(supers);
    }

    @Override
    public List<SuperHuman> getAllSuperHumans() {
        List<SuperHuman> supers
                = jdbcTemplate.query(SQL_SELECT_ALL_SUPERHUMANS,
                        new SuperHumanMapper());
        return associateOrganizationsWithSuperHumans(supers);
    }

    @Override
    @Transactional
    public void addOrganization(Organization organization) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getAddress());

        int organizationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        organization.setOrganizationId(organizationId);
    }

    @Override
    @Transactional
    public void deleteOrganization(int organizationId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_SUPERHUMAN, organizationId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, organizationId);
    }

    @Override
    public void updateOrganization(Organization organization) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getPhone(),
                organization.getAddress(),
                organization.getOrganizationId());
    }

    @Override
    public Organization getOrganizationById(int organizationId) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(), organizationId);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setLocationId(rs.getInt("locationId"));
            loc.setName(rs.getString("name"));
            loc.setDescription(rs.getString("description"));
            loc.setAddress(rs.getString("address"));
            loc.setLatitude(rs.getBigDecimal("latitude"));
            loc.setLongitude(rs.getBigDecimal("longitude"));
            return loc;

        }

    }

    private static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting si = new Sighting();
            si.setSightingId(rs.getInt("sightingId"));
            si.setDate(rs.getDate("date"));
            return si;

        }

    }

    private static final class SuperHumanMapper implements RowMapper<SuperHuman> {

        @Override
        public SuperHuman mapRow(ResultSet rs, int i) throws SQLException {
            SuperHuman sh = new SuperHuman();
            sh.setSuperHumanId(rs.getInt("superHumanId"));
            sh.setName(rs.getString("name"));
            sh.setDescription(rs.getString("description"));
            sh.setPowers(rs.getString("powers"));
            return sh;

        }

    }

    private static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setOrganizationId(rs.getInt("organizationId"));
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setPhone(rs.getString("phone"));
            org.setAddress(rs.getString("address"));
            return org;

        }

    }

}
