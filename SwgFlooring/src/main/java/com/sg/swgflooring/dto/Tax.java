/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author jakeduerr
 */
public class Tax {
    
    private String state;
    private BigDecimal TaxRate;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.state);
        hash = 41 * hash + Objects.hashCode(this.TaxRate);
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
        final Tax other = (Tax) obj;
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.TaxRate, other.TaxRate)) {
            return false;
        }
        return true;
    }
    
}
