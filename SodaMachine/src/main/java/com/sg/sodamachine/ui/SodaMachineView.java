/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.ui;

import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachinePersistenceException;
import com.sg.sodamachine.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineView {

    private UserIO io;
    private SodaMachineDao dao;

    public SodaMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {

        io.print("***Main Menu***");
        io.print("1. Display Beverages and Prices");
        io.print("2. Purchase Beverage");
        io.print("3. Exit");

        return io.readInt("Please select from the above choices.", 1, 3);

    }

    public void displaySodasSelection(List<Soda> sodaList) {
        sodaList.stream()
                .forEach(s -> System.out.println(s.getSodaName() + ": "
                + s.getSodaCost() + ": "
                + s.getNumOfSoda()));

    }

    public String displayMoneyAndGetSodaSelection() {
        System.out.println(getMoneyAmount());
        return io.readString("Please enter your selection of Soda: ");

    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displaySodaMenuBanner() {
        io.print("*** Beverages & Prices ***");
    }

    public void displaySodaPurchaseBanner() {
        io.print("*** Purchase a Beverage ***");
    }

    public void displayExitBanner() {
        io.print("Thank you and good bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("!!! Unknown Command !!!");
    }

    public BigDecimal getMoneyAmount() {

        Scanner sc = new Scanner(System.in);
        BigDecimal userMoney = sc.nextBigDecimal();
        return userMoney;

    }

    public void checkMoneyAmount() throws SodaMachinePersistenceException {
        BigDecimal userMoney = getMoneyAmount();
        String userSoda = displayMoneyAndGetSodaSelection();
        Soda soda = dao.getSodaCost(userSoda);

        if (userMoney <   ) {
            io.print("Insufficient Funds");
        } else {

        }
    }

    public void diplaySoda(Soda soda) {
        if (soda != null) {
            io.print(soda.getSodaName() + ":  "
                    + soda.getSodaCost() + ":  "
                    + soda.getNumOfSoda());
            io.print("");
        } else {
            io.print("Beverage does not exist.");
        }
        io.readString("Please hit enter to continue.");
    }

}
