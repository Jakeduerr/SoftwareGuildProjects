/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.advice;

import com.sg.sodamachine.dao.SodaMachineAuditDao;
import com.sg.sodamachine.dao.SodaMachineAuditDaoFileImpl;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jakeduerr
 */
public class LoggingAdvice {

    SodaMachineAuditDaoFileImpl auditDao;

    public LoggingAdvice(SodaMachineAuditDaoFileImpl auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp, Throwable ex) throws SodaMachinePersistenceException {
        
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " : ";
            auditEntry += args[0];
            try {
                auditDao.writeAuditEntry(auditEntry, ex);
            } catch (SodaMachinePersistenceException e) {
                System.err.println(e.getMessage());
            }
      
    }
}
