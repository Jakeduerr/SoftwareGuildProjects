/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachineDaoStubImpl;
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
public class SodaMachineServiceLayerTest {

    private SodaMachineServiceLayer service;

    public SodaMachineServiceLayerTest() {
        SodaMachineDao dao = new SodaMachineDaoStubImpl();

        service = new SodaMachineServiceLayerImpl(dao);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of calculateChange method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testCalculateChange() throws Exception {
        //test1
        BigDecimal quarter = new BigDecimal("1");
        BigDecimal dime = new BigDecimal("1");
        BigDecimal nickel = new BigDecimal("1");
        BigDecimal penny = new BigDecimal("1");

        BigDecimal[] expectedArray = {quarter, dime, nickel, penny};
        BigDecimal[] changeArray;
        changeArray = service.calculateChange(new BigDecimal("91"), service.getSodaCost("Coke"));

        assertArrayEquals(expectedArray, changeArray);

        //test2
        BigDecimal quarter2 = new BigDecimal("1");
        BigDecimal dime2 = new BigDecimal("1");
        BigDecimal nickel2 = new BigDecimal("1");
        BigDecimal penny2 = new BigDecimal("0");

        BigDecimal[] expectedArray2 = {quarter2, dime2, nickel2, penny2};
        BigDecimal[] changeArray2;
        changeArray2 = service.calculateChange(new BigDecimal("90"), service.getSodaCost("Coke"));

        assertArrayEquals(expectedArray2, changeArray2);

        //test3
        BigDecimal quarter3 = new BigDecimal("0");
        BigDecimal dime3 = new BigDecimal("1");
        BigDecimal nickel3 = new BigDecimal("1");
        BigDecimal penny3 = new BigDecimal("1");

        BigDecimal[] expectedArray3 = {quarter3, dime3, nickel3, penny3};
        BigDecimal[] changeArray3;
        changeArray3 = service.calculateChange(new BigDecimal("66"), service.getSodaCost("Coke"));

        assertArrayEquals(expectedArray3, changeArray3);

    }

    /**
     * Test of checkLimitOfMoney method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testCheckLimitOfMoney() throws Exception {
        BigDecimal dollarLimit = new BigDecimal("100");

        try {
            service.checkLimitOfMoney(new BigDecimal("101"), dollarLimit);
            fail("Expected SodaMachineTooMuchMoneyException was not thrown.");
        } catch (SodaMachineTooMuchMoneyException ex) {

        }
    }

    /**
     * Test of checkUserInput method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testCheckUserInput() throws Exception {

        try {
            service.checkUserInput(service.getSodaCost("Coke"), new BigDecimal("25"));
            fail("Expected SodaMachineInsufficientFundsException was not thrown.");
        } catch (SodaMachineInsufficientFundsException ex) {

        }

    }

    /**
     * Test of checkUserInput method, of class SodaMachineServiceLayer.
     */
    @Test
    public void checkSodaSelection() throws Exception {

        try {
            service.checkSodaSelection(service.getSoda("Fish"));
            fail("Expected SodaMachineUnknownSodaException was not thrown.");
        } catch (SodaMachineUnknownSodaException ex) {

        }

    }

    /**
     * Test of checkInventory method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testCheckInventory() throws Exception {

        try {
            service.checkInventory("Coke");
            fail("Expected SodaMachineNoItemInventoryException was not thrown.");
        } catch (SodaMachineNoItemInventoryException ex) {

        }

    }

    /**
     * Test of getAllSoda method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testGetAllSoda() throws Exception {
    }

    /**
     * Test of getSoda method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testGetSoda() throws Exception {
    }

    /**
     * Test of updateSoda method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testUpdateSoda() throws Exception {
    }

    /**
     * Test of getSodaCost method, of class SodaMachineServiceLayer.
     */
    @Test
    public void testGetSodaCost() throws Exception {
    }

}
