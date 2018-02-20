/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.service;

import com.sg.swgflooring.dao.SwgFlooringPersistenceException;
import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SwgFlooringServiceLayer {
    
    BigDecimal calculateMaterialCost(Product product, BigDecimal areaOfMaterial)
            throws SwgFlooringPersistenceException;

    BigDecimal calculateLaborCost(Product product, BigDecimal areaOfMaterial)
            throws SwgFlooringPersistenceException;

    BigDecimal calculateTotalTax(BigDecimal materialCost, BigDecimal laborCost, Tax tax)
            throws SwgFlooringPersistenceException;

    BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax)
            throws SwgFlooringPersistenceException;
    
    boolean checkProductInput(String productType) throws SwgFlooringPersistenceException;
    
    boolean checkStateInput(String state) throws SwgFlooringPersistenceException;
    
    boolean checkAreaOfMaterial(BigDecimal areaOfMaterial) throws SwgFlooringPersistenceException;
    
    boolean checkOrdersByDate(LocalDate orderdate) throws SwgFlooringPersistenceException;
    
    boolean checkOrders(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;

    List<Product> getProductsList() throws SwgFlooringPersistenceException;

    Product getProduct(String productType) throws SwgFlooringPersistenceException;

    List<Tax> getTaxesList() throws SwgFlooringPersistenceException;

    Tax getTax(String state) throws SwgFlooringPersistenceException;
    
    void addNewOrder(Order order) throws SwgFlooringPersistenceException;

    void saveAllOrders() throws SwgFlooringPersistenceException;

    int setOrderNumber(LocalDate orderDate) throws SwgFlooringPersistenceException;

    List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException;

    Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;

    void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;

}
