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
    
    boolean getComfirmation(String userChoice) throws SwgFlooringPersistenceException;
    
    void setNewOrderNumber(Order order) throws SwgFlooringPersistenceException;
    
    boolean validateOrderExists(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;
    
    BigDecimal getProductTypeAndSetCost(String userMaterial) throws SwgFlooringPersistenceException;
    
    BigDecimal getProductTypeAndSetLaborCost(String userMaterial) throws SwgFlooringPersistenceException;
    
    BigDecimal calculateMaterialCost(Product product, BigDecimal areaOfMaterial) 
            throws SwgFlooringPersistenceException;
    
    BigDecimal calculateLaborCost(Product product, BigDecimal areaOfMaterial) 
            throws SwgFlooringPersistenceException;
    
    BigDecimal calculateTotalTax(BigDecimal materialCost, BigDecimal laborCost, Tax tax) 
            throws SwgFlooringPersistenceException;
    
    BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) 
            throws SwgFlooringPersistenceException;
    
    List<Product> getProductsList() throws SwgFlooringPersistenceException;
    
    Product getProduct(String productType) throws SwgFlooringPersistenceException;
    
    List<Tax> getTaxesList() throws SwgFlooringPersistenceException;
    
    Tax getTax(String state) throws SwgFlooringPersistenceException;
    
    List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException;

    Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;
    
    void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;
    
   
    
}
