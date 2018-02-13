/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SodaMachineServiceLayer {
    
    BigDecimal[] calculateChange(BigDecimal userInput, BigDecimal itemPrice);
    
    boolean checkLimitOfMoney(BigDecimal userInput, BigDecimal dollarLimit) throws SodaMachineTooMuchMoneyException;
    
    void checkUserInput(BigDecimal itemPrice, BigDecimal userInput) throws SodaMachinePersistenceException, 
            SodaMachineInsufficientFundsException;
    
    void checkSodaSelection(Soda soda) throws SodaMachinePersistenceException, 
            SodaMachineUnknownSodaException;
    
    void checkInventory(String sodaName) throws SodaMachinePersistenceException, 
            SodaMachineNoItemInventoryException;
    
    List<Soda> getAllSoda() throws SodaMachinePersistenceException;
    
    Soda getSoda(String sodaName) throws SodaMachinePersistenceException;
    
    void updateSoda(String sodaName) throws SodaMachinePersistenceException;
    
    BigDecimal getSodaCost(String sodaName) throws SodaMachinePersistenceException;
    
}
