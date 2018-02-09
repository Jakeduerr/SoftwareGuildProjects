/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

import com.sg.sodamachine.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineDaoStubImpl implements SodaMachineDao {

    private Soda onlySoda = new Soda("Gatoradex");
    private Soda anotherSoda = new Soda("Coke");
    private List<Soda> sodaList = new ArrayList<>();

    public SodaMachineDaoStubImpl() {
        onlySoda.setSodaCost(new BigDecimal("50"));
        onlySoda.setNumOfSoda(2);
        anotherSoda.setSodaCost(new BigDecimal("50"));
        anotherSoda.setNumOfSoda(0);
    }

    @Override
    public List<Soda> getAllSoda() throws SodaMachinePersistenceException {
        return sodaList;
    }

    @Override
    public Soda getSoda(String sodaName) throws SodaMachinePersistenceException {
        if (sodaName.equals(onlySoda.getSodaName())) {
            return onlySoda;
        } else if (sodaName.equals(anotherSoda.getSodaName())) {

            return anotherSoda;
        }
        return null;
    }

    @Override
    public void updateSoda(String sodaName) throws SodaMachinePersistenceException {
        onlySoda.setNumOfSoda(1);
    }

    @Override
    public BigDecimal getSodaCost(String sodaName) throws SodaMachinePersistenceException {
        return onlySoda.getSodaCost();
    }

    @Override
    public void stockSoda() throws SodaMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
