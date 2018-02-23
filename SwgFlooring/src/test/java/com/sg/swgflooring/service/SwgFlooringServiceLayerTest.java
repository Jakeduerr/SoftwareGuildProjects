/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.service;

import com.sg.swgflooring.dao.SwgFlooringAuditDao;
import com.sg.swgflooring.dao.SwgFlooringAuditDaoStubImpl;
import com.sg.swgflooring.dao.SwgFlooringDao;
import com.sg.swgflooring.dao.SwgFlooringDaoFileImpl;
import com.sg.swgflooring.dao.SwgFlooringPersistenceException;
import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
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
public class SwgFlooringServiceLayerTest {

    private SwgFlooringServiceLayer service;
    private LocalDate testDate = LocalDate.of(1990, 02, 23);

    public SwgFlooringServiceLayerTest() {

        SwgFlooringDao dao = new SwgFlooringDaoFileImpl();
        SwgFlooringAuditDao auditDao = new SwgFlooringAuditDaoStubImpl();

        service = new SwgFlooringServiceLayerImpl(dao, auditDao);

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
     * Test of calculateMaterialCost method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCalculateMaterialCost() throws Exception {
        Product product = new Product();
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        BigDecimal areaOfMaterial = new BigDecimal("10");
        BigDecimal expectedResult = new BigDecimal("22.50");

        BigDecimal result = service.calculateMaterialCost(product, areaOfMaterial);

        assertEquals(expectedResult, result);
    }

    /**
     * Test of calculateLaborCost method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCalculateLaborCost() throws Exception {
        Product product = new Product();
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        BigDecimal areaOfMaterial = new BigDecimal("10");
        BigDecimal expectedResult = new BigDecimal("21.00");

        BigDecimal result = service.calculateLaborCost(product, areaOfMaterial);

        assertEquals(expectedResult, result);

    }

    /**
     * Test of calculateTotalTax method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCalculateTotalTax() throws Exception {
        Tax tax = new Tax();
        tax.setTaxRate(new BigDecimal("6.25"));

        Product product = new Product();
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        BigDecimal areaOfMaterial1 = new BigDecimal("10");

        BigDecimal result1 = service.calculateMaterialCost(product, areaOfMaterial1);

        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        BigDecimal areaOfMaterial2 = new BigDecimal("10");

        BigDecimal result2 = service.calculateLaborCost(product, areaOfMaterial2);

        BigDecimal result3 = service.calculateTotalTax(result1, result2, tax);
        BigDecimal expectedResult = new BigDecimal("2.71875");

        assertEquals(expectedResult, result3);
    }

    /**
     * Test of calculateTotalCost method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCalculateTotalCost() throws Exception {
        Tax tax = new Tax();
        tax.setTaxRate(new BigDecimal("6.25"));

        Product product = new Product();
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        BigDecimal areaOfMaterial1 = new BigDecimal("10");

        BigDecimal result1 = service.calculateMaterialCost(product, areaOfMaterial1);

        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        BigDecimal areaOfMaterial2 = new BigDecimal("10");

        BigDecimal result2 = service.calculateLaborCost(product, areaOfMaterial2);

        BigDecimal result3 = service.calculateTotalTax(result1, result2, tax);
        
        BigDecimal result4 = service.calculateTotalCost(result1, result2, result3);
        BigDecimal expectedResult = new BigDecimal("46.21875");
        
        assertEquals(expectedResult, result4);
    }

    /**
     * Test of checkProductInput method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCheckProductInput() throws Exception {
        boolean test = service.checkProductInput("Wood");
        assertTrue(test);
        
        boolean test2 = service.checkProductInput("food");
        assertFalse(test2);
        
    }

    /**
     * Test of checkStateInput method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCheckStateInput() throws Exception {
        boolean test = service.checkStateInput("OH");
        assertTrue(test);
        
        boolean test2 = service.checkStateInput("food");
        assertFalse(test2);
        
    }

    /**
     * Test of checkAreaOfMaterial method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCheckAreaOfMaterial() throws Exception {
        BigDecimal testArea = new BigDecimal("1");
        boolean test = service.checkAreaOfMaterial(testArea);
        assertTrue(test);
        
        BigDecimal testArea2 = new BigDecimal("-1");
        boolean test2 = service.checkAreaOfMaterial(testArea2);
        assertFalse(test2);
        
    }

    /**
     * Test of checkOrdersByDate method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCheckOrdersByDate() throws Exception {
        boolean test = service.checkOrdersByDate(testDate);
        assertTrue(test);
        
        try {
            service.checkOrdersByDate(LocalDate.of(1990, 01, 23));
            fail("Expected SwgFlooringPersistenceException was not thrown.");
        } catch (SwgFlooringPersistenceException ex) {
            
        }
        
    }

    /**
     * Test of checkOrders method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testCheckOrders() throws Exception {
        boolean test = service.checkOrders(testDate, 1);
        assertTrue(test);
        
        try {
            service.checkOrders(LocalDate.of(1990, 01, 23), 1);
            fail("Expected SwgFlooringPersistenceException was not thrown.");
        } catch (SwgFlooringPersistenceException ex) {
            
        }
        
    }

    /**
     * Test of getProductsList method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testGetProductsList() throws Exception {
    }

    /**
     * Test of getProduct method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testGetProduct() throws Exception {
    }

    /**
     * Test of getTaxesList method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testGetTaxesList() throws Exception {
    }

    /**
     * Test of getTax method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testGetTax() throws Exception {
    }

    /**
     * Test of addNewOrder method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testAddNewOrder() throws Exception {
    }

    /**
     * Test of saveAllOrders method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testSaveAllOrders() throws Exception {
    }

    /**
     * Test of setOrderNumber method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testSetOrderNumber() throws Exception {
    }

    /**
     * Test of listOrdersByDate method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testListOrdersByDate() throws Exception {
    }

    /**
     * Test of getOrder method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testGetOrder() throws Exception {
    }

    /**
     * Test of removeOrder method, of class SwgFlooringServiceLayer.
     */
    @Test
    public void testRemoveOrder() throws Exception {
    }

}
