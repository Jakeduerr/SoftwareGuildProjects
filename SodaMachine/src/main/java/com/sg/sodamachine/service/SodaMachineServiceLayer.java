/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SodaMachineServiceLayer {
    
    void addSoda(Soda soda) throws SodaMachineInsufficientFundsException, 
            SodaMachineNoItemInventoryException, 
            SodaMachinePersistenceException;
    
    List<Soda> getAllSoda() throws SodaMachinePersistenceException;
    
    Soda getSoda(String sodaName) throws SodaMachinePersistenceException;
    
    Soda removeSoda(String sodaName) throws SodaMachinePersistenceException;
    
    BigDecimal getSodaCost(BigDecimal sodaCost) throws SodaMachinePersistenceException;
    
}
