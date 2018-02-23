/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.io.File;
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
    private LocalDate testDate = LocalDate.of(1990, 02, 23);
    
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

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getProductsList method, of class SwgFlooringDao.
     */
    @Test
    public void testGetProductsList() throws Exception {
        List<Product> testList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductType("Carpet");
        product1.setCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testList.add(product1);
        Product product2 = new Product();
        product2.setProductType("Laminate");
        product2.setCostPerSquareFoot(new BigDecimal("1.75"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testList.add(product2);
        Product product3 = new Product();
        product3.setProductType("Tile");
        product3.setCostPerSquareFoot(new BigDecimal("3.50"));
        product3.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        testList.add(product3);
        Product product4 = new Product();
        product4.setProductType("Wood");
        product4.setCostPerSquareFoot(new BigDecimal("5.15"));
        product4.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        testList.add(product4);
        
        List<Product> products = dao.getProductsList();
        
        assertEquals(testList, products);
        
    }

    /**
     * Test of getProduct method, of class SwgFlooringDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        List<Product> testList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductType("Carpet");
        product1.setCostPerSquareFoot(new BigDecimal("2.25"));
        product1.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testList.add(product1);
        Product product2 = new Product();
        product2.setProductType("Laminate");
        product2.setCostPerSquareFoot(new BigDecimal("1.75"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("2.10"));
        testList.add(product2);
        Product product3 = new Product();
        product3.setProductType("Tile");
        product3.setCostPerSquareFoot(new BigDecimal("3.50"));
        product3.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        testList.add(product3);
        Product product4 = new Product();
        product4.setProductType("Wood");
        product4.setCostPerSquareFoot(new BigDecimal("5.15"));
        product4.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        testList.add(product4);
        
        product4 = dao.getProduct("wood");
        
        Product product = dao.getProduct("wood");
        
        assertEquals(product4, product);
        
        String productType5 = "food";
        Product product5 = dao.getProduct(productType5);
        
        assertNull(product5);
        
    }

    /**
     * Test of getTaxesList method, of class SwgFlooringDao.
     */
    @Test
    public void testGetTaxesList() throws Exception {
        List<Tax> testList = new ArrayList<>();
        Tax tax1 = new Tax();
        tax1.setState("OH");
        tax1.setTaxRate(new BigDecimal("6.25"));
        testList.add(tax1);
        Tax tax2 = new Tax();
        tax2.setState("PA");
        tax2.setTaxRate(new BigDecimal("6.75"));
        testList.add(tax2);
        Tax tax3 = new Tax();
        tax3.setState("MI");
        tax3.setTaxRate(new BigDecimal("5.75"));
        testList.add(tax3);
        Tax tax4 = new Tax();
        tax4.setState("IN");
        tax4.setTaxRate(new BigDecimal("6.00"));
        testList.add(tax4);
        
        List<Tax> taxes = dao.getTaxesList();
        
        assertEquals(testList, taxes);
        
    }

    /**
     * Test of getTax method, of class SwgFlooringDao.
     */
    @Test
    public void testGetTax() throws Exception {
        List<Tax> testList = new ArrayList<>();
        Tax tax1 = new Tax();
        tax1.setState("OH");
        tax1.setTaxRate(new BigDecimal("6.25"));
        testList.add(tax1);
        Tax tax2 = new Tax();
        tax2.setState("PA");
        tax2.setTaxRate(new BigDecimal("6.75"));
        testList.add(tax2);
        Tax tax3 = new Tax();
        tax3.setState("MI");
        tax3.setTaxRate(new BigDecimal("5.75"));
        testList.add(tax3);
        Tax tax4 = new Tax();
        tax4.setState("IN");
        tax4.setTaxRate(new BigDecimal("6.00"));
        testList.add(tax4);
        
        tax4 = dao.getTax("wood");
        
        Tax tax = dao.getTax("wood");
        
        assertEquals(tax4, tax);
        
        String state5 = "food";
        Tax tax5 = dao.getTax(state5);
        
        assertNull(tax5);
        
    }

    /**
     * Test of setOrderNumber method, of class SwgFlooringDao.
     */
    @Test
    public void testSetOrderNumber() throws Exception {
        int orderNumber = dao.setOrderNumber(testDate);
        
        assertEquals(11, orderNumber);
        
        int testNumber = dao.setOrderNumber(LocalDate.of(2000, 03, 24));
        
        assertEquals(1, testNumber);
        
    }

    /**
     * Test of addNewOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testAddNewOrder() throws Exception {
        Order testOrder = new Order(testDate);
        testOrder.setOrderDate(testDate);
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
        
        Order test2Order = dao.getOrder(testDate, 1);
        
        assertEquals(testOrder, test2Order);
        
    }

    /**
     * Test of saveOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testSaveOrder() throws Exception {
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(testDate);
        
        dao.saveOrder(dateList);
        
        File testFile = new File("Orders_02231990.txt");
        boolean exists = testFile.exists();
        
        assertTrue(exists);
        
    }

    /**
     * Test of listAllDates method, of class SwgFlooringDao.
     */
    @Test
    public void testListAllDates() throws Exception {
        Order testOrder = new Order(testDate);
        Order testOrder2 = new Order(LocalDate.of(2000, 04, 05));
        List<LocalDate> dateList = new ArrayList<>();
        dateList.add(testDate);
        dateList.add(LocalDate.of(2000, 04, 05));
        
        dao.listAllDates();
        
        assertEquals(2, dateList.size());
        
        
    }

    /**
     * Test of listOrdersByDate method, of class SwgFlooringDao.
     */
    @Test
    public void testListOrdersByDate() throws Exception {
        List<Order> orderList = dao.listOrdersByDate(testDate);
        
        assertEquals(10, orderList.size());
        
        List<Order> orderList2 = dao.listOrdersByDate(testDate);
        
        assertNotEquals(5, orderList2.size());
        
    }

    /**
     * Test of getOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testGetOrder() throws Exception {
        Order testOrder = new Order(testDate);
        testOrder.setOrderDate(testDate);
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
        
        testOrder = dao.getOrder(testDate, 1);
        
        Order test2Order = dao.getOrder(testDate, 1);
        
        
        assertEquals(testOrder, test2Order);   
        
    }

    /**
     * Test of removeOrder method, of class SwgFlooringDao.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        Order testOrder = new Order(testDate);
        testOrder.setOrderDate(testDate);
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
        
        Order testOrder2 = new Order(testDate);
        testOrder2.setOrderDate(testDate);
        testOrder2.setOrderNumber(2);
        testOrder2.setCustomerName("Wise");
        Tax tax2 = new Tax();
        tax2.setState("OH");
        tax2.setTaxRate(new BigDecimal("6.25"));
        testOrder2.setTax(tax);
        Product product2 = new Product();
        product2.setProductType("Wood");
        testOrder2.setAreaOfMaterial(new BigDecimal("100"));
        product2.setCostPerSquareFoot(new BigDecimal("5.15"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        testOrder2.setProduct(product);
        testOrder2.setMaterialCost(new BigDecimal("515.00"));
        testOrder2.setTotalLaborCost(new BigDecimal("475.00"));
        testOrder2.setTotalTax(new BigDecimal("61.88"));
        testOrder2.setTotalCost(new BigDecimal("1051.88"));
        
        dao.addNewOrder(testOrder);
        dao.addNewOrder(testOrder2);
        dao.removeOrder(testDate, 2);
        Order otherOrder = dao.getOrder(testDate, 2);
        
        assertEquals(null, otherOrder);
        
    }
}
