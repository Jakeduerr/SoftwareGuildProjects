/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringAuditDaoFileImpl implements SwgFlooringAuditDao {
    
    public static final String AUDIT_FILE = "audit.txt";

    @Override
    public void writeAuditEntry(String entry, Throwable ex) throws SwgFlooringPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));

        } catch (IOException e) {
            throw new SwgFlooringPersistenceException("Could not persist audit information.", e);
        }
        
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry + " : " + ex.getClass().getName());
        out.flush();

    }
    
}
