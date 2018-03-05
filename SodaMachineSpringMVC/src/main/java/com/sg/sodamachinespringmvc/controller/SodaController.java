/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachinespringmvc.controller;

import com.sg.sodamachinespringmvc.dao.SodaMachineDao;
import com.sg.sodamachinespringmvc.dao.SodaMachinePersistenceException;
import com.sg.sodamachinespringmvc.model.Soda;
import com.sg.sodamachinespringmvc.service.SodaMachineServiceLayer;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jakeduerr
 */
@Controller
public class SodaController {
    
//    @Inject
//    SodaMachineServiceLayer service;

    
    SodaMachineServiceLayer service;
    
    @Inject
    public SodaController(SodaMachineServiceLayer service) {
        this.service = service;
    }

    //@RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String displaySodaPage(Model model) throws SodaMachinePersistenceException {
        List<Soda> sodaList = service.getAllSoda();

        model.addAttribute("sodaList", sodaList);
        
        return "index";
    }
    
    @GetMapping("/")
    public void purchaseSode() throws SodaMachinePersistenceException {
        //confused how to actually implement the functionality of the controller with the jsps
    }

}
