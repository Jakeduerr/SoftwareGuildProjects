/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachinespringmvc.service;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineNoItemInventoryException extends Exception {
    
    public SodaMachineNoItemInventoryException(String message) {
        super(message);
    }
    
    public SodaMachineNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
