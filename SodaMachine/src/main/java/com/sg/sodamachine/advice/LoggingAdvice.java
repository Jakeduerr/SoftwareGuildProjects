/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.advice;

import com.sg.sodamachine.dao.SodaMachineAuditDao;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.service.SodaMachineNoItemInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author jakeduerr
 */
public class LoggingAdvice {

    SodaMachineAuditDao auditDao;

    public LoggingAdvice(SodaMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) throws SodaMachinePersistenceException {
        Object[] args = jp.getArgs();
        int inventoryOfSoda = jp.getSignature().getName().hashCode();
        for (int i = 0; i < args.length; i++) {
            Object argument = args[i];
            if (inventoryOfSoda <= 0) {
                auditDao.writeAuditEntry("SodaMachineNoItemInventoryException" + " : " + argument);
            }
        }
    }

    public void createAuditEntry2(JoinPoint jp) throws SodaMachinePersistenceException {
        Object[] args = jp.getArgs();
        for (int i = 1; i < args.length; i++) {
            Object argument = args[0];
            Object argument2 = args[1];
            int itemPrice = argument.hashCode();
            int userInput = argument2.hashCode();
            if (itemPrice > userInput) {
                auditDao.writeAuditEntry("SodaMachineInsufficientFundsException" + " : " + argument2 + "¢ entered");
            }
        }
    }
//        Object[] args = jp.getArgs();
//        String auditEntry = jp.getSignature().getName() + " : ";
//        for (Object currentArg : args) {
//            auditEntry += currentArg;
//
//            try {
//                auditDao.writeAuditEntry(auditEntry);
//            } catch (SodaMachinePersistenceException ex) {
//                System.err.println("ERROR: Could not create audit entry in LoggingAdvice.");
//            }
//
//        }

//        Object[] arguments = jp.getArgs();
//        for (int i = 0; i < arguments.length; i++) {
//            Object argument = arguments[i];
//            if (argument == null) {
//                auditDao.writeAuditEntry(jp.getSignature().getClass().toString());
//            }
//        }
}
