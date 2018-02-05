/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

import com.sg.sodamachine.service.SodaMachineInsufficientFundsException;
import com.sg.sodamachine.service.SodaMachineNoItemInventoryException;
import com.sg.sodamachine.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SodaMachineDao {
    
    public Soda addSoda(String sodaName, Soda soda) throws SodaMachineInsufficientFundsException, 
            SodaMachineNoItemInventoryException, 
            SodaMachinePersistenceException;
    
    public List<Soda> getAllSoda() throws SodaMachinePersistenceException;
    
    public Soda getSoda(String sodaName) throws SodaMachinePersistenceException;
    
    public Soda removeSoda(Soda sodaName) throws SodaMachinePersistenceException;
    
    public BigDecimal getSodaCost(BigDecimal sodaCost) throws SodaMachinePersistenceException;
    
}
