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
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface SwgFlooringDao {

    List<Product> getProductsList() throws SwgFlooringPersistenceException;

    Product getProduct(String productType) throws SwgFlooringPersistenceException;

    List<Tax> getTaxesList() throws SwgFlooringPersistenceException;

    Tax getTax(String state) throws SwgFlooringPersistenceException;

    int setOrderNumber(LocalDate orderDate) throws SwgFlooringPersistenceException;

    void addNewOrder(Order order) throws SwgFlooringPersistenceException;

    void saveOrder(List<LocalDate> orderDateList) throws SwgFlooringPersistenceException;

    List<LocalDate> listAllDates() throws SwgFlooringPersistenceException;

    List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException;

    Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;

    void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException;

}
