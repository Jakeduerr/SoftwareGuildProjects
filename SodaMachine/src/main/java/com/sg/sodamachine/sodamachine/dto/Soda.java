/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.sodamachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author jakeduerr
 */
public class Soda {

    private String sodaName;
    private BigDecimal sodaCost;
    private int numOfSoda;

    public Soda(String sodaName) {
        this.sodaName = sodaName;
    }

    public String getSodaName() {
        return sodaName;
    }

    public BigDecimal getSodaCost() {
        return sodaCost;
    }

    public void setSodaCost(BigDecimal sodaCost) {
        this.sodaCost = sodaCost;
    }

    public int getNumOfSoda() {
        return numOfSoda;
    }

    public void setNumOfSoda(int numOfSoda) {
        this.numOfSoda = numOfSoda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.sodaName);
        hash = 53 * hash + Objects.hashCode(this.sodaCost);
        hash = 53 * hash + this.numOfSoda;
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
        final Soda other = (Soda) obj;
        if (this.numOfSoda != other.numOfSoda) {
            return false;
        }
        if (!Objects.equals(this.sodaName, other.sodaName)) {
            return false;
        }
        if (!Objects.equals(this.sodaCost, other.sodaCost)) {
            return false;
        }
        return true;
    }

}
