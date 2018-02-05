/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

/**
 *
 * @author jakeduerr
 */
public class SodaMachinePersistenceException extends Exception {
    
    public SodaMachinePersistenceException(String message) {
        super(message);
    }
    
    public SodaMachinePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
