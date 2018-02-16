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
    public boolean getComfirmation(String userChoice) throws SwgFlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNewOrderNumber(Order order) throws SwgFlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateOrderExists(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getProductTypeAndSetCost(String userMaterial) throws SwgFlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal getProductTypeAndSetLaborCost(String userMaterial) throws SwgFlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        BigDecimal totalTax = materialCost.add(laborCost).multiply(taxRate);
        return totalTax;
    }

    @Override
    public BigDecimal calculateTotalCost(BigDecimal materialCost, BigDecimal laborCost, BigDecimal tax) throws SwgFlooringPersistenceException {
        BigDecimal totalCost = materialCost.add(laborCost).add(tax);
        return totalCost;
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
