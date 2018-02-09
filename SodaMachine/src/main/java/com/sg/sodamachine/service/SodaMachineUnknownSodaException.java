/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.service;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineUnknownSodaException extends Exception {

   public SodaMachineUnknownSodaException(String message) {
        super(message);
    }
    
    public SodaMachineUnknownSodaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
