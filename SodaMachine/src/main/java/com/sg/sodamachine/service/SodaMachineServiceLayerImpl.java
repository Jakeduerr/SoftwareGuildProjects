/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

import com.sg.sodamachine.dao.SodaMachineAuditDao;
import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineServiceLayerImpl implements SodaMachineServiceLayer {
    
    private SodaMachineAuditDao auditDao;
    private SodaMachineDao dao;

    public SodaMachineServiceLayerImpl(SodaMachineDao dao, SodaMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public BigDecimal[] calculateChange(BigDecimal userInput, BigDecimal itemPrice) {

        BigDecimal quarter = new BigDecimal("25");
        BigDecimal dime = new BigDecimal("10");
        BigDecimal nickel = new BigDecimal("5");
        BigDecimal penny = new BigDecimal("1");
        BigDecimal changeGiven1;
        BigDecimal changeGiven2;
        BigDecimal changeGiven3;
        BigDecimal changeGiven4;
        BigDecimal howManyQuarters = new BigDecimal("0.0");
        BigDecimal howManyDimes = new BigDecimal("0.0");
        BigDecimal howManyNickels = new BigDecimal("0.0");
        BigDecimal howManyPennies = new BigDecimal("0.0");

        changeGiven1 = userInput.subtract(itemPrice);
        howManyQuarters = changeGiven1.divide(quarter, 0, RoundingMode.DOWN);
        changeGiven2 = changeGiven1.subtract(howManyQuarters.multiply(quarter));
        howManyDimes = changeGiven2.divide(dime, 0, RoundingMode.DOWN);
        changeGiven3 = changeGiven2.subtract(howManyDimes.multiply(dime));
        howManyNickels = changeGiven3.divide(nickel, 0, RoundingMode.DOWN);
        changeGiven4 = changeGiven3.subtract(howManyNickels.multiply(nickel));
        howManyPennies = changeGiven4.divide(penny, 0, RoundingMode.DOWN);

        BigDecimal[] changeArray = {howManyQuarters, howManyDimes, howManyNickels, howManyPennies};

        return changeArray;

    }

    @Override
    public boolean checkLimitOfMoney(BigDecimal userInput, BigDecimal dollarLimit) throws SodaMachineTooMuchMoneyException {

        if (userInput.compareTo(dollarLimit) > 0) {
            throw new SodaMachineTooMuchMoneyException("Sorry this machine doesn't take more than $1 in cents.");
        }
        return true;
    }

    @Override
    public void checkUserInput(BigDecimal itemPrice, BigDecimal userInput)
            throws SodaMachinePersistenceException,
            SodaMachineInsufficientFundsException {

        if (itemPrice.compareTo(userInput) > 0) {
            throw new SodaMachineInsufficientFundsException("Not enough money, please enter more. You entered " + userInput + "¢.");
            
        }
        
    }

    @Override
    public void checkSodaSelection(Soda soda) throws SodaMachinePersistenceException, SodaMachineUnknownSodaException {

        if (soda == null) {
            throw new SodaMachineUnknownSodaException("Sorry, we do not carry that soda.");
        }

    }

    @Override
    public void checkInventory(String sodaName) throws SodaMachinePersistenceException, SodaMachineNoItemInventoryException {

        int checkInt = dao.getSoda(sodaName).getNumOfSoda();
        if (checkInt <= 0) {
            throw new SodaMachineNoItemInventoryException("Sorry, we are out of that soda.");

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
