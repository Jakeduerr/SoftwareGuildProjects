/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import com.sg.swgflooring.dao.SwgFlooringDao;
import com.sg.swgflooring.dao.SwgFlooringPersistenceException;
import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringDaoStubImpl implements SwgFlooringDao {
    
    Order testOrder;
    List<Order> orderList = new ArrayList<>();
    List<LocalDate> dateList = new ArrayList<>();
    List<Product> productList = new ArrayList<>();
    List<Tax> taxList = new ArrayList<>();
   
    public SwgFlooringDaoStubImpl() {
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
        
        orderList.add(testOrder);
    }

    @Override
    public List<Product> getProductsList() throws SwgFlooringPersistenceException {
        return productList;
    }

    @Override
    public Product getProduct(String productType) throws SwgFlooringPersistenceException {
        if(testOrder.getProduct().getProductType().equalsIgnoreCase(productType)) {
            return testOrder.getProduct();
        } else {
            return null;
        }
    }

    @Override
    public List<Tax> getTaxesList() throws SwgFlooringPersistenceException {
        return taxList;
    }

    @Override
    public Tax getTax(String state) throws SwgFlooringPersistenceException {
        if(testOrder.getTax().getState().equalsIgnoreCase(state)) {
            return testOrder.getTax();
        } else {
            return null;
        }
    }

    @Override
    public int setOrderNumber(LocalDate orderDate) throws SwgFlooringPersistenceException {
        if(testOrder.getOrderDate().equals(orderDate)) {
            return testOrder.getOrderNumber();
        } else {
            return 1;
        }
    }

    @Override
    public void addNewOrder(Order order) throws SwgFlooringPersistenceException {
        if(order.getOrderDate().equals(testOrder.getOrderDate())) {
            orderList.add(order);
        }
     }

    @Override
    public void saveOrder(List<LocalDate> orderDateList) throws SwgFlooringPersistenceException {
        if(orderDateList.equals(dateList)) {
            
            
        }
    }

    @Override
    public List<LocalDate> listAllDates() throws SwgFlooringPersistenceException {
        return dateList;
    }

    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException {
        if(orderDate.equals(testOrder.getOrderDate())) {
            orderList.add(testOrder);
            return orderList;
        } else {
            return null;
        }
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        if(orderDate.equals(testOrder.getOrderDate())) {
            return testOrder;
        } else {
            return null;
        }
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        Iterator<Order> itr = orderList.iterator();
        while (itr.hasNext()) {
            Order order = itr.next();
            if (order.getOrderDate().equals(orderDate) && order.getOrderNumber() == (orderNumber)) {
                itr.remove();
            }
        }
    }
    
}
