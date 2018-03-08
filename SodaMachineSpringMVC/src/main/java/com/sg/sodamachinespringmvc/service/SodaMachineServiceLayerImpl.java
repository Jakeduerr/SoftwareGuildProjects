/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachinespringmvc.service;

import com.sg.sodamachinespringmvc.controller.SodaController;
import com.sg.sodamachinespringmvc.dao.SodaMachineDao;
import com.sg.sodamachinespringmvc.dao.SodaMachinePersistenceException;
import com.sg.sodamachinespringmvc.model.Soda;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author jakeduerr
 */
@Component
public class SodaMachineServiceLayerImpl implements SodaMachineServiceLayer {

    @Inject
    SodaMachineDao dao;

    @Override
    public BigDecimal[] calculateChange(BigDecimal userInput, BigDecimal itemPrice) {

        BigDecimal quarter = new BigDecimal("25");
        BigDecimal dime = new BigDecimal("10");
        BigDecimal nickel = new BigDecimal("5");
        BigDecimal penny = new BigDecimal("1");

        BigDecimal howManyQuarters = new BigDecimal("0.0");
        BigDecimal howManyDimes = new BigDecimal("0.0");
        BigDecimal howManyNickels = new BigDecimal("0.0");
        BigDecimal howManyPennies = new BigDecimal("0.0");

        BigDecimal changeGiven1 = userInput.subtract(itemPrice);
        BigDecimal newUserInput = changeGiven1.multiply(new BigDecimal("100"));

        howManyQuarters = newUserInput.divide(quarter, 0, RoundingMode.DOWN);
        BigDecimal changeGiven2 = newUserInput.subtract(howManyQuarters.multiply(quarter));
        howManyDimes = changeGiven2.divide(dime, 0, RoundingMode.DOWN);
        BigDecimal changeGiven3 = changeGiven2.subtract(howManyDimes.multiply(dime));
        howManyNickels = changeGiven3.divide(nickel, 0, RoundingMode.DOWN);
        BigDecimal changeGiven4 = changeGiven3.subtract(howManyNickels.multiply(nickel));
        howManyPennies = changeGiven4.divide(penny, 0, RoundingMode.DOWN);

        BigDecimal[] changeArray = {howManyQuarters, howManyDimes, howManyNickels, howManyPennies};

        return changeArray;

    }

    @Override
    public void checkUserInput(BigDecimal itemPrice, BigDecimal userInput)
            throws SodaMachinePersistenceException,
            SodaMachineInsufficientFundsException {

        if (itemPrice.compareTo(userInput) > 0) {
            throw new SodaMachineInsufficientFundsException("Please deposit: $");

        }

    }

    @Override
    public void checkInventory(String sodaName) throws SodaMachinePersistenceException, SodaMachineNoItemInventoryException {

        int checkInt = dao.getSoda(sodaName).getNumOfSoda();
        if (checkInt <= 0) {
            throw new SodaMachineNoItemInventoryException("SOLD OUT!!!");

        }

    }

    @Override
    public List<Soda> getAllSoda() throws SodaMachinePersistenceException {
        return dao.getAllSoda();
    }

    @Override
    public Soda getSoda(String sodaName) throws SodaMachinePersistenceException {
        return dao.getSoda(sodaName);
    }

    @Override
    public void updateSoda(String sodaName) throws SodaMachinePersistenceException {
        dao.updateSoda(sodaName);
    }

    @Override
    public BigDecimal getSodaCost(String sodaName) throws SodaMachinePersistenceException {
        return dao.getSodaCost(sodaName);
    }

}
