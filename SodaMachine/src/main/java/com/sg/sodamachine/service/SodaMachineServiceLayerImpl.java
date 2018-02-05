/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.sodamachine.dto.Soda;
import com.sg.sodamachine.ui.SodaMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineServiceLayerImpl implements SodaMachineServiceLayer {

    SodaMachineDao dao;
    

    public SodaMachineServiceLayerImpl(SodaMachineDao dao) {
        this.dao = dao;
        
    }
    
    

    private void validateSodaData(Soda soda) throws
            SodaMachinePersistenceException {

        if (soda.getSodaName() == null
                || soda.getSodaName().trim().length() == 0) {
            throw new SodaMachinePersistenceException(
                    "ERROR ");
        }
    }

    @Override
    public void addSoda(Soda soda) throws
            SodaMachineInsufficientFundsException,
            SodaMachineNoItemInventoryException,
            SodaMachinePersistenceException {
        if (dao.getSoda(soda.getSodaName()) != null) {
            throw new SodaMachinePersistenceException(
                    "ERROR: Could not Add soda. "
                    + soda.getSodaName()
                    + " already exists");
        }
        validateSodaData(soda);
        dao.addSoda(soda.getSodaName(), soda);

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
    public Soda removeSoda(String sodaName) throws SodaMachinePersistenceException {
        return dao.removeSoda(sodaName);
    }

    @Override
    public BigDecimal getSodaCost(BigDecimal sodaCost) throws SodaMachinePersistenceException {
        return dao.getSodaCost(sodaCost);
    }

}
