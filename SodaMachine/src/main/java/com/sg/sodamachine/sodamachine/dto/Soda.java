/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.sodamachine.dto;

import java.math.BigDecimal;

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

}
