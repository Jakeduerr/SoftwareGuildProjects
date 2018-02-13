/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.ui;

import com.sg.sodamachine.dto.Soda;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineView {

    private UserIO io;

    public SodaMachineView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {

        io.print("*** Main Menu ***");
        io.print("1. Purchase Beverage");
        io.print("2. Exit");

        return io.readInt("Please select from the above choices.", 1, 2);

    }

    public void displaySodasSelection(List<Soda> newList) {
        newList.stream()
                .forEach(s -> System.out.println(s.getSodaName() + "  ****  "
                + s.getSodaCost() + "¢" + "  ****  "
                + s.getNumOfSoda()));
    }

    public String getSodaSelection() {
        return io.readString("Please enter your selection of Soda: ");
    }

    public BigDecimal getMoneyAmount() {
        String userPurchase = io.readString("Please enter your money in Cents: $1 dollar or less only.");
        BigDecimal userMoney = new BigDecimal(userPurchase);
        return userMoney;
    }

    public void displayPurchaseResult(BigDecimal[] changeResult) {
        io.print("Vending now...");

        io.print("Your change is: " + changeResult[0] + " Quarter(s) || "
                + changeResult[1] + " Dime(s) || "
                + changeResult[2] + "  Nickel(s) || "
                + changeResult[3] + " Penny(s)");

        io.print("Thank you, and enjoy!");

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

}
