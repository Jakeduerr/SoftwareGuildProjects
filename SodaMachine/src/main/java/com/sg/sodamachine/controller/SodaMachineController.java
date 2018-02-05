/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.controller;

import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.dao.SodaMachineDaoFileImpl;
import com.sg.sodamachine.service.SodaMachineServiceLayer;
import com.sg.sodamachine.sodamachine.dto.Soda;
import com.sg.sodamachine.ui.SodaMachineView;
import com.sg.sodamachine.ui.UserIO;
import com.sg.sodamachine.ui.UserIOConsoleImpl;
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

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        getSodaDisplay();
                        break;
                    case 2:
                        purchaseSoda();
                        break;
                    case 3:
                        askAgain = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (SodaMachinePersistenceException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void getSodaDisplay() throws SodaMachinePersistenceException {
        List<Soda> sodaList = service.getAllSoda();
        view.displaySodasSelection(sodaList);

    }

    private void purchaseSoda() throws SodaMachinePersistenceException {
        view.displaySodaPurchaseBanner();
        List<Soda> sodaList = service.getAllSoda();
        view.displaySodasSelection(sodaList);
        String userSelection = view.displayMoneyAndGetSodaSelection();
        
        
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
