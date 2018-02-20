/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import com.sg.swgflooring.dao.SwgFlooringAuditDao;
import com.sg.swgflooring.dao.SwgFlooringPersistenceException;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringAuditDaoStubImpl implements SwgFlooringAuditDao {

    @Override
    public void writeAuditEntry(String entry, Throwable ex) throws SwgFlooringPersistenceException {
        //do nothing
    }
    
}
