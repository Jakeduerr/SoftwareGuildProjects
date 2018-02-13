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
public interface SodaMachineAuditDao {
    
        public void writeAuditEntry(String entry, Throwable ex) throws SodaMachinePersistenceException;

}
