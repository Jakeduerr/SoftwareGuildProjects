/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.service;

import com.sg.swgflooring.dao.SwgFlooringAuditDao;
import com.sg.swgflooring.dao.SwgFlooringDao;
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
public class SwgFlooringServiceLayerImpl implements SwgFlooringServiceLayer {

    SwgFlooringDao dao;
    SwgFlooringAuditDao auditDao;

    public SwgFlooringServiceLayerImpl(SwgFlooringDao dao, SwgFlooringAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;

    }

    @Override
    public BigDecimal calculateMaterialCost(Product product, BigDecimal areaOfMaterial) throws SwgFlooringPersistenceException {
        BigDecimal costPerSquareFoot = product.getCostPerSquareFoot();
        BigDecimal materialCost = (costPerSquareFoot).multiply(areaOfMaterial);
        return materialCost;

    }

    @Override
    public BigDecimal calculateLaborCost(Product product, BigDecimal areaOfMaterial) throws SwgFlooringPersistenceException {
        BigDecimal laborCostPerSquareFoot = product.getLaborCostPerSquareFoot();
        BigDecimal laborCost = (laborCostPerSquareFoot).multiply(areaOfMaterial);
        return laborCost;

    }

    @Override
    public BigDecimal calculateTotalTax(BigDecimal materialCost, BigDecimal laborCost, Tax tax) throws SwgFlooringPersistenceException {
        BigDecimal taxRate = tax.getTaxRate();
        BigDecimal matAndLabCost = (materialCost).add(laborCost);
        BigDecimal totalTax = (matAndLabCost).multiply(taxRate).divide(new BigDecimal("100"));
        return totalTax;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) throws SwgFlooringPersistenceException {
        BigDecimal totalCost = materialCost.add(laborCost).add(tax);
        return totalCost;
    }

    @Override
    public boolean checkProductInput(String productType) throws SwgFlooringPersistenceException {
        if (null == dao.getProduct(productType)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkStateInput(String state) throws SwgFlooringPersistenceException {
        if (null == dao.getTax(state)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkAreaOfMaterial(BigDecimal areaOfMaterial) throws SwgFlooringPersistenceException {
        BigDecimal limit = new BigDecimal("0.0");
        if (limit.compareTo(areaOfMaterial) > 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException {
        if (null == dao.listOrdersByDate(orderDate)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkOrders(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        if (null == dao.getOrder(orderDate, orderNumber)) {
            return false;
        }
        return true;
    }

    @Override
    public List<Product> getProductsList() throws SwgFlooringPersistenceException {
        return dao.getProductsList();
    }

    @Override
    public Product getProduct(String productType) throws SwgFlooringPersistenceException {
        return dao.getProduct(productType);
    }

    @Override
    public List<Tax> getTaxesList() throws SwgFlooringPersistenceException {
        return dao.getTaxesList();
    }

    @Override
    public Tax getTax(String state) throws SwgFlooringPersistenceException {
        return dao.getTax(state);
    }

    @Override
    public void addNewOrder(Order order) throws SwgFlooringPersistenceException {
        dao.addNewOrder(order);
    }

    @Override
    public void saveAllOrders() throws SwgFlooringPersistenceException {
        List<LocalDate> datesOfOrders = dao.listAllDates();
        dao.saveOrder(datesOfOrders);

    }

    @Override
    public int setOrderNumber(LocalDate orderDate) throws SwgFlooringPersistenceException {
        return dao.setOrderNumber(orderDate);
    }

    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException {
        return dao.listOrdersByDate(orderDate);
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        return dao.getOrder(orderDate, orderNumber);
    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        dao.removeOrder(orderDate, orderNumber);
    }

}
