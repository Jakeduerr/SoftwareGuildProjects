/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachinespringmvc.dao;

import com.sg.sodamachinespringmvc.model.Soda;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SodaMachineDao {

    List<Soda> getAllSoda() throws SodaMachinePersistenceException;

    Soda getSoda(String sodaName) throws SodaMachinePersistenceException;

    void updateSoda(String sodaName) throws SodaMachinePersistenceException;
    
    BigDecimal getSodaCost(String sodaName) throws SodaMachinePersistenceException;

    void stockSoda() throws SodaMachinePersistenceException;
}
