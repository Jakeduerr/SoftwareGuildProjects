/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachinespringmvc.controller;

import com.sg.sodamachinespringmvc.dao.SodaMachineDao;
import com.sg.sodamachinespringmvc.dao.SodaMachinePersistenceException;
import com.sg.sodamachinespringmvc.model.Soda;
import com.sg.sodamachinespringmvc.service.SodaMachineInsufficientFundsException;
import com.sg.sodamachinespringmvc.service.SodaMachineNoItemInventoryException;
import com.sg.sodamachinespringmvc.service.SodaMachineServiceLayer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jakeduerr
 */
@Controller
public class SodaController {

    @Inject
    SodaMachineServiceLayer service;

    public BigDecimal totalMoney = new BigDecimal("0.0");
    public String message;
    public Soda selectedSoda;
    public BigDecimal[] totalChange;
    public String change;

    @GetMapping("/")
    public String displaySodaPage(Model model) throws SodaMachinePersistenceException {
        List<Soda> sodaList = service.getAllSoda();

        model.addAttribute("sodaList", sodaList);
        model.addAttribute("totalMoney", totalMoney.setScale(2, RoundingMode.HALF_UP));
        model.addAttribute("selectedSoda", selectedSoda);
        model.addAttribute("message", message);
        model.addAttribute("totalChange", change);

        return "index";
    }

    @PostMapping("/addDollar")
    public String addDollar() throws SodaMachinePersistenceException {
        BigDecimal dollar = new BigDecimal("1");
        totalMoney = totalMoney.add(dollar);
        return "redirect:/";

    }

    @PostMapping("/addQuarter")
    public String addQuarter() throws SodaMachinePersistenceException {
        BigDecimal quarter = new BigDecimal(".25");
        totalMoney = totalMoney.add(quarter);
        return "redirect:/";

    }

    @PostMapping("/addDime")
    public String addDime() throws SodaMachinePersistenceException {
        BigDecimal dime = new BigDecimal(".10");
        totalMoney = totalMoney.add(dime);
        return "redirect:/";

    }

    @PostMapping("/addNickel")
    public String addNickel() throws SodaMachinePersistenceException {
        BigDecimal nickel = new BigDecimal(".05");
        totalMoney = totalMoney.add(nickel);
        return "redirect:/";

    }

    @PostMapping("/selectSoda")
    public String selectSoda(String sodaName) throws SodaMachinePersistenceException {
        selectedSoda = service.getSoda(sodaName);
        return "redirect:/";

    }

    @PostMapping("/makePurchase")
    public String makePurchase() throws SodaMachinePersistenceException {
        if (selectedSoda == null) {
            message = "You must select an item.";
            return "redirect:/";
        } else {

            try {
                service.checkInventory(selectedSoda.getSodaName());
            } catch (SodaMachineNoItemInventoryException ex) {
                message = ex.getMessage();
                return "redirect:/";
            }

            try {
                service.checkUserInput(service.getSodaCost(selectedSoda.getSodaName()), totalMoney);
            } catch (SodaMachineInsufficientFundsException ex) {
                BigDecimal costDifference = selectedSoda.getSodaCost().subtract(totalMoney);
                message = ex.getMessage() + costDifference;
                return "redirect:/";
            }

            service.updateSoda(selectedSoda.getSodaName());
            totalChange = service.calculateChange(totalMoney, service.getSodaCost(selectedSoda.getSodaName()));
            change = "Quarters: " + totalChange[0]
                    + " Dimes: " + totalChange[1]
                    + " Nickels: " + totalChange[2]
                    + " Pennies: " + totalChange[3];
            message = "Thank You!!!";
            totalMoney = (new BigDecimal("0.0"));

            return "redirect:/";
        }

    }

    @PostMapping("/returnChange")
    public String returnChange() throws SodaMachinePersistenceException {
        BigDecimal noItem = new BigDecimal("0.0");
        if (totalMoney.compareTo(new BigDecimal("0.0")) > 0 && selectedSoda == null) {
            totalChange = service.calculateChange(totalMoney, noItem);
            totalMoney = new BigDecimal("0.0");
            change = "Quarters: " + totalChange[0]
                    + " Dimes: " + totalChange[1]
                    + " Nickels: " + totalChange[2]
                    + " Pennies: " + totalChange[3];

        } else {
            change = ("");
        }

        totalMoney = new BigDecimal("0.0");
        message = ("");
        selectedSoda = null;

        return "redirect:/";

    }

}
