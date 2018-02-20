/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jakeduerr
 */
public class Order {
    
    private int orderNumber;
    private String customerName;
    private BigDecimal areaOfMaterial;
    private BigDecimal materialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal totalCost;
    private LocalDate orderDate;
    private Product product;
    private Tax tax;

    
    public Order(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
    
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getAreaOfMaterial() {
        return areaOfMaterial;
    }

    public void setAreaOfMaterial(BigDecimal areaOfMaterial) {
        this.areaOfMaterial = areaOfMaterial;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        orderDate = LocalDate.now();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.orderNumber;
        hash = 23 * hash + Objects.hashCode(this.customerName);
        hash = 23 * hash + Objects.hashCode(this.areaOfMaterial);
        hash = 23 * hash + Objects.hashCode(this.materialCost);
        hash = 23 * hash + Objects.hashCode(this.totalLaborCost);
        hash = 23 * hash + Objects.hashCode(this.totalTax);
        hash = 23 * hash + Objects.hashCode(this.totalCost);
        hash = 23 * hash + Objects.hashCode(this.orderDate);
        hash = 23 * hash + Objects.hashCode(this.product);
        hash = 23 * hash + Objects.hashCode(this.tax);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.areaOfMaterial, other.areaOfMaterial)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.totalLaborCost, other.totalLaborCost)) {
            return false;
        }
        if (!Objects.equals(this.totalTax, other.totalTax)) {
            return false;
        }
        if (!Objects.equals(this.totalCost, other.totalCost)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (!Objects.equals(this.tax, other.tax)) {
            return false;
        }
        return true;
    }
}
