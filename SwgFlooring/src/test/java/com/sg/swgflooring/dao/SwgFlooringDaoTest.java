/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
public class SwgFlooringDaoTest {
    
    private SwgFlooringDao dao = new SwgFlooringDaoFileImpl();
    
    public SwgFlooringDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
//        Order testOrder = new Order(LocalDate.now());
//        testOrder.setOrderDate(LocalDate.now());
//        testOrder.setOrderNumber(1);
//        testOrder.setCustomerName("Wise");
//        Tax tax = new Tax();
//        tax.setState("OH");
//        tax.setTaxRate(new BigDecimal("6.25"));
//        testOrder.setTax(tax);
//        Product product = new Product();
//        product.setProductType("Wood");
//        testOrder.setAreaOfMaterial(new BigDecimal("100"));
//        product.setCostPerSquareFoot(new BigDecimal("5.15"));
//        product.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
//        testOrder.setProduct(product);
//        testOrder.setMaterialCost(new BigDecimal("515.00"));
//        testOrder.setTotalLaborCost(new BigDecimal("475.00"));
//        testOrder.setTotalTax(new BigDecimal("61.88"));
//        testOrder.setTotalCost(new BigDecimal("1051.88"));
  


    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProductsList method, of class SwgFlooringDao.
     */
    @Test
    public void testGetProductsList() throws Exception {
        
        
        
    }

    /**
     * Test of getProduct method, of class SwgFlooringDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        
        
        
    }

    /**
     * Test of getTaxesList method, of class SwgFlooringDao.
     */
    @Test
    public void testGetTaxesList() throws Exception {
        
        
        
    }

    /**
     * Test of getTax method, of class SwgFlooringDao.
     */
    @Test
    public void testGetTax() throws Exception {
        
        
        
    }

    /**
     * Test of setOrderNumber method, of class SwgFlooringDao.
     */
    @Test
    public void testSetOrderNumber() throws Exception {
        
        
        
    }

    /**
     * Test of addNewOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testAddNewOrder() throws Exception {
        Order testOrder = new Order(LocalDate.now());
        testOrder.setOrderDate(LocalDate.now());
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("Wise");
        Tax tax = new Tax();
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("6.25"));
        testOrder.setTax(tax);
        Product product = new Product();
        product.setProductType("Wood");
        testOrder.setAreaOfMaterial(new BigDecimal("100"));
        product.setCostPerSquareFoot(new BigDecimal("5.15"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        testOrder.setProduct(product);
        testOrder.setMaterialCost(new BigDecimal("515.00"));
        testOrder.setTotalLaborCost(new BigDecimal("475.00"));
        testOrder.setTotalTax(new BigDecimal("61.88"));
        testOrder.setTotalCost(new BigDecimal("1051.88"));
        
        dao.addNewOrder(testOrder);
        
        Order test2Order = new Order(LocalDate.now());
        dao.getOrder(test2Order.getOrderDate(), 1);
        dao.addNewOrder(test2Order);
        
        assertEquals(testOrder, test2Order);
    }

    /**
     * Test of saveOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testSaveOrder() throws Exception {
        
        
        
    }

    /**
     * Test of listAllDates method, of class SwgFlooringDao.
     */
    @Test
    public void testListAllDates() throws Exception {
        
        
        
    }

    /**
     * Test of listOrdersByDate method, of class SwgFlooringDao.
     */
    @Test
    public void testListOrdersByDate() throws Exception {
        
        
        
    }

    /**
     * Test of getOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        
        Order testOrder = new Order(LocalDate.now());
        testOrder.setOrderDate(LocalDate.now());
        testOrder.setOrderNumber(1);
        testOrder.setCustomerName("Wise");
        Tax tax = new Tax();
        tax.setState("OH");
        tax.setTaxRate(new BigDecimal("6.25"));
        testOrder.setTax(tax);
        Product product = new Product();
        product.setProductType("Wood");
        testOrder.setAreaOfMaterial(new BigDecimal("100"));
        product.setCostPerSquareFoot(new BigDecimal("5.15"));
        product.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        testOrder.setProduct(product);
        testOrder.setMaterialCost(new BigDecimal("515.00"));
        testOrder.setTotalLaborCost(new BigDecimal("475.00"));
        testOrder.setTotalTax(new BigDecimal("61.88"));
        testOrder.setTotalCost(new BigDecimal("1051.88"));
        
        dao.getOrder(testOrder.getOrderDate(), testOrder.getOrderNumber());
        
        Order test2Order = new Order(LocalDate.now());
        dao.getOrder(test2Order.getOrderDate(), test2Order.getOrderNumber());
        
        assertEquals(testOrder, test2Order);
        
    }

    /**
     * Test of removeOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        
        
        
    }
}
