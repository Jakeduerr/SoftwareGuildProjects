/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperHuman;
import com.sg.supersightings.service.SuperSightingsServiceLayerImpl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author jakeduerr
 */
@Controller
public class SuperSightingsController {

    @Inject
    SuperSightingsServiceLayerImpl service;

    @GetMapping("/")
    public String displayHomePage(Model model) throws SuperSightingsPersistenceException {
        List<Sighting> sightings = service.findTenMostRecentSightings();

        model.addAttribute("sightings", sightings);

        return "index";
    }

    //////////////////////////// LOCATION METHODS //////////////////////////////
    @GetMapping("/displayLocationsPage")
    public String displayLocationsPage(Model model) throws SuperSightingsPersistenceException {
        List<Location> locals = service.getAllLocations();

        model.addAttribute("locals", locals);

        return "location";
    }

    @PostMapping("/createLocation")
    public String createLocation(@Valid Location location, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<Location> locals = service.getAllLocations();

            model.addAttribute("locals", locals);

            return "location";

        }

        service.addLocation(location);
        return "redirect:displayLocationsPage";
    }

    @GetMapping("/displayLocationDetails")
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);
        model.addAttribute("location", location);

        List<Sighting> sightings = service.findSightingsForLocation(location);
        model.addAttribute("sightings", sightings);

        return "viewLocationDetails";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        service.deleteLocation(locationId);

        return "redirect:displayLocationsPage";
    }

    @GetMapping("/displayEditLocationForm")
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);
        model.addAttribute("location", location);

        return "editLocationForm";
    }

    @PostMapping("/editLocation")
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<Location> locals = service.getAllLocations();

            model.addAttribute("locals", locals);
            
            return "editLocationForm";
        }

        service.updateLocation(location);

        return "redirect:displayLocationsPage";
    }

    //////////////////////////// SIGHTING METHODS //////////////////////////////
    @GetMapping("/displaySightingsPage")
    public String displaySightingPage(Model model) throws SuperSightingsPersistenceException {
        List<Sighting> sightings = service.getAllSightings();
        List<Location> locals = service.getAllLocations();
        List<SuperHuman> superHumans = service.getAllSuperHumans();

        model.addAttribute("sightings", sightings);
        model.addAttribute("locals", locals);
        model.addAttribute("superHumans", superHumans);

        return "sighting";
    }

    @PostMapping("/createSighting")
    public String createSighting(HttpServletRequest request, Model model) throws Exception {
//        if (result.hasErrors()) {
//            model.addAttribute("errors", result.getAllErrors());
//
//            List<Sighting> sightings = service.getAllSightings();
//            List<Location> locals = service.getAllLocations();
//            List<SuperHuman> superHumans = service.getAllSuperHumans();
//
//            model.addAttribute("sightings", sightings);
//            model.addAttribute("locals", locals);
//            model.addAttribute("superHumans", superHumans);
//
//            return "sighting";
//        }
        
        Sighting sighting = new Sighting();
        String sightingDate = request.getParameter("date");
        sighting.setDate(Date.valueOf(sightingDate));

        String[] localsIDList = request.getParameterValues("locals");
        for (String localID : localsIDList) {
            sighting.setLocation(service.getLocationById(Integer.parseInt(localID)));
        }

        String[] superIDList = request.getParameterValues("superHumans");
        List<SuperHuman> supers = new ArrayList<>();
        for (String superID : superIDList) {
            supers.add(service.getSuperHumanById(Integer.parseInt(superID)));
        }

        sighting.setSuperHumans(supers);

        service.addSighting(sighting);
        return "redirect:displaySightingsPage";
    }

    @GetMapping("/displaySightingDetails")
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = service.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);

        List<SuperHuman> superHumans = service.findSuperHumansForSighting(sighting);
        model.addAttribute("superHumans", superHumans);

        return "viewSightingDetails";
    }

    @GetMapping("/deleteSighting")
    public String deleteSighting(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        service.deleteSighting(sightingId);

        return "redirect:displaySightingsPage";
    }

    @GetMapping("/displayEditSightingForm")
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);

        Sighting sighting = service.getSightingById(sightingId);
        List<Location> locals = service.getAllLocations();
        List<SuperHuman> supers = service.getAllSuperHumans();
        model.addAttribute("sighting", sighting);
        model.addAttribute("locations", locals);
        model.addAttribute("superHumans", supers);

        return "editSightingForm";
    }

    @PostMapping("/editSighting")
    public String editSighting(HttpServletRequest request) {

        Sighting sighting = new Sighting();
        sighting.setSightingId(Integer.parseInt(request.getParameter("sightingId")));
        String sightingDate = request.getParameter("date");
        sighting.setDate(Date.valueOf(sightingDate));

        String localID = request.getParameter("location");
        sighting.setLocation(service.getLocationById(Integer.parseInt(localID)));

        String[] superIDList = request.getParameterValues("superHumans");
        List<SuperHuman> supers = new ArrayList<>();
        for (String superID : superIDList) {
            supers.add(service.getSuperHumanById(Integer.parseInt(superID)));
        }
        sighting.setSuperHumans(supers);

        service.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }

    //////////////////////////// SUPERHUMAN METHODS ////////////////////////////
    @GetMapping("/displaySuperHumansPage")
    public String displaySuperHumansPage(Model model) throws SuperSightingsPersistenceException {
        List<SuperHuman> supers = service.getAllSuperHumans();
        List<Organization> organizations = service.getAllOrganizations();

        model.addAttribute("supers", supers);
        model.addAttribute("organizations", organizations);

        return "superHuman";
    }

    @PostMapping("/createSuperHuman")
    public String createSuperHuman(@Valid SuperHuman superHuman, BindingResult result, HttpServletRequest request, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<SuperHuman> supers = service.getAllSuperHumans();
            List<Organization> organizations = service.getAllOrganizations();

            model.addAttribute("supers", supers);
            model.addAttribute("organizations", organizations);

            return "superHuman";
        }

        String[] orgIDList = request.getParameterValues("orgs");
        List<Organization> orgs = new ArrayList<>();
        for (String orgID : orgIDList) {
            orgs.add(service.getOrganizationById(Integer.parseInt(orgID)));
        }

        superHuman.setOrganizations(orgs);

        service.addSuperHuman(superHuman);
        return "redirect:displaySuperHumansPage";
    }

    @GetMapping("/displaySuperHumanDetails")
    public String displaySuperHumanDetails(HttpServletRequest request, Model model) {
        String superHumanIdParameter = request.getParameter("superHumanId");
        int superHumanId = Integer.parseInt(superHumanIdParameter);

        SuperHuman superHuman = service.getSuperHumanById(superHumanId);
        model.addAttribute("superHuman", superHuman);

        List<Sighting> sights = service.getSightingsBySuperHumanId(superHumanId);
        model.addAttribute("sights", sights);

        return "viewSuperHumanDetails";
    }

    @GetMapping("/deleteSuperHuman")
    public String deleteSuperHuman(HttpServletRequest request, Model model) {
        String superHumanIdParameter = request.getParameter("superHumanId");
        int superHumanId = Integer.parseInt(superHumanIdParameter);

        service.deleteSuperHuman(superHumanId);

        return "redirect:displaySuperHumansPage";
    }

    @GetMapping("/displayEditSuperHumanForm")
    public String displayEditSuperHumanForm(HttpServletRequest request, Model model) {
        String superHumanIdParameter = request.getParameter("superHumanId");
        int superHumanId = Integer.parseInt(superHumanIdParameter);

        SuperHuman superHuman = service.getSuperHumanById(superHumanId);
        List<Organization> orgs = service.getAllOrganizations();
        model.addAttribute("superHuman", superHuman);
        model.addAttribute("organizations", orgs);

        return "editSuperHumanForm";
    }

    @PostMapping("/editSuperHuman")
    public String editSuperHuman(@Valid @ModelAttribute("superHuman") SuperHuman superHuman, BindingResult result, HttpServletRequest request, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<Organization> orgs = service.getAllOrganizations();
            model.addAttribute("organizations", orgs);

            return "editSuperHumanForm";
        }
        String[] orgIDList = request.getParameterValues("orgs");
        List<Organization> orgs = new ArrayList<>();
        for (String orgID : orgIDList) {
            orgs.add(service.getOrganizationById(Integer.parseInt(orgID)));
        }

        superHuman.setOrganizations(orgs);

        service.updateSuperHuman(superHuman);

        return "redirect:displaySuperHumansPage";
    }

    //////////////////////////// ORGANIZATION METHODS //////////////////////////
    @GetMapping("/displayOrganizationsPage")
    public String displayOrganizationsPage(Model model) throws SuperSightingsPersistenceException {
        List<Organization> orgs = service.getAllOrganizations();

        model.addAttribute("orgs", orgs);

        return "organization";
    }

    @PostMapping("/createOrganization")
    public String createOrganization(@Valid Organization organization, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<Organization> orgs = service.getAllOrganizations();

            model.addAttribute("orgs", orgs);

            return "organization";
        }

        service.addOrganization(organization);
        return "redirect:displayOrganizationsPage";
    }

    @GetMapping("/displayOrganizationDetails")
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = service.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);

        List<SuperHuman> supers = service.findSuperHumansForOrganization(organization);
        model.addAttribute("supers", supers);

        return "viewOrganizationDetails";
    }

    @GetMapping("/deleteOrganization")
    public String deleteOrganization(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        service.deleteOrganization(organizationId);

        return "redirect:displayOrganizationsPage";
    }

    @GetMapping("/displayEditOrganizationForm")
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization organization = service.getOrganizationById(organizationId);
        model.addAttribute("organization", organization);

        return "editOrganizationForm";
    }

    @PostMapping("/editOrganization")
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());

            List<Organization> orgs = service.getAllOrganizations();

            model.addAttribute("orgs", orgs);
            
            return "editOrganization";
        }

        service.updateOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }

}
