/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

import com.sg.sodamachine.dto.Soda;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineDaoTest {

    private SodaMachineDao dao = new SodaMachineDaoFileImpl();

    public SodaMachineDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        dao.stockSoda();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllSoda method, of class SodaMachineDao.
     */
    @Test
    public void testGetAllSoda() throws Exception {

        assertEquals(10, dao.getAllSoda().size());

    }

    /**
     * Test of getSoda method, of class SodaMachineDao.
     */
    @Test
    public void testGetSoda() throws Exception {

        Soda soda = dao.getSoda("Gatorade");
        Soda expectedSoda = new Soda("Gatorade");
        expectedSoda.setSodaCost(new BigDecimal("75"));
        expectedSoda.setNumOfSoda(2);

        assertEquals(soda, expectedSoda);
    }

    /**
     * Test of updateSoda method, of class SodaMachineDao.
     */
    @Test
    public void testUpdateSoda() throws Exception {

        for (Soda soda : dao.getAllSoda()) {
            dao.updateSoda(soda.getSodaName());
        }

        for (Soda soda : dao.getAllSoda()) {
            assertEquals(1, soda.getNumOfSoda());
        }

    }

    /**
     * Test of getSodaCost method, of class SodaMachineDao.
     */
    @Test
    public void testGetSodaCost() throws Exception {
        Soda soda = dao.getSoda("Gatorade");
        Soda expectedSoda = new Soda("Gatorade");
        expectedSoda.setSodaCost(new BigDecimal("75"));

        assertEquals(soda.getSodaCost(), expectedSoda.getSodaCost());

    }

}
