/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.controller;

import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.service.SodaMachineInsufficientFundsException;
import com.sg.sodamachine.service.SodaMachineNoItemInventoryException;
import com.sg.sodamachine.service.SodaMachineServiceLayer;
import com.sg.sodamachine.service.SodaMachineTooMuchMoneyException;
import com.sg.sodamachine.service.SodaMachineUnknownSodaException;
import com.sg.sodamachine.sodamachine.dto.Soda;
import com.sg.sodamachine.ui.SodaMachineView;
import com.sg.sodamachine.ui.UserIO;
import com.sg.sodamachine.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineController {

    private SodaMachineView view;
    private SodaMachineServiceLayer service;
    private UserIO io = new UserIOConsoleImpl();

    public SodaMachineController(SodaMachineServiceLayer service, SodaMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        boolean askAgain = true;
        int menuSelection = 0;
        try {
            while (askAgain) {
                view.displaySodaMenuBanner();
                getSodaDisplay();
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        purchaseSoda();
                        break;
                    case 2:
                        askAgain = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (SodaMachinePersistenceException
                | SodaMachineTooMuchMoneyException
                | SodaMachineInsufficientFundsException
                | SodaMachineNoItemInventoryException
                | SodaMachineUnknownSodaException ex) {
            view.displayErrorMessage(ex.getMessage());
        }

    }

    private void purchaseSoda() throws SodaMachinePersistenceException,
            SodaMachineTooMuchMoneyException,
            SodaMachineInsufficientFundsException,
            SodaMachineNoItemInventoryException,
            SodaMachineUnknownSodaException {

        view.displaySodaPurchaseBanner();
        BigDecimal dollarLimit = new BigDecimal("100");
        BigDecimal userInput = view.getMoneyAmount();
        service.checkLimitOfMoney(userInput, dollarLimit);
        String userSoda = view.getSodaSelection();
        Soda soda = service.getSoda(userSoda);
        service.checkSodaSelection(soda);
        BigDecimal itemPrice = service.getSodaCost(userSoda);
        service.checkInventory(userSoda);
        service.checkUserInput(itemPrice, userInput);
        service.updateSoda(userSoda);
        BigDecimal[] changeResult = service.calculateChange(userInput, itemPrice);
        view.displayPurchaseResult(changeResult);

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void getSodaDisplay() throws SodaMachinePersistenceException {
        List<Soda> newList = service.getAllSoda();
        view.displaySodasSelection(newList);

    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
