/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.controller;

import com.sg.swgflooring.dao.SwgFlooringPersistenceException;
import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import com.sg.swgflooring.service.SwgFlooringServiceLayer;
import com.sg.swgflooring.ui.SwgFlooringView;
import com.sg.swgflooring.ui.UserIO;
import com.sg.swgflooring.ui.UserIOConsoleImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringController {

    SwgFlooringServiceLayer service;
    SwgFlooringView view;
    private UserIO io = new UserIOConsoleImpl();

    public SwgFlooringController(SwgFlooringServiceLayer service, SwgFlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean askAgain = true;
        int menuSelection;

        while (askAgain) {
            try {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
                        displayCurrentProductsAndStates();
                        addNewOrder();
                        break;
                    case 3:
                        editAnOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        saveAllOrders();
                        break;
                    case 6:
                        saveAllOrders();
                        askAgain = false;
                        break;
                    default:
                        unknownCommand();

                }
            } catch (SwgFlooringPersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
        exitMessage();

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    private int getMenuSelection() {
        return view.displayMenuAndGetSelection();
    }

    private void displayCurrentProductsAndStates() throws SwgFlooringPersistenceException {
        List<Product> productList = service.getProductsList();
        view.displayListOfProducts(productList);
        List<Tax> stateList = service.getTaxesList();
        view.displayListOfStates(stateList);
    }

    private void displayOrdersByDate() throws SwgFlooringPersistenceException {
        view.displayOrdersBanner();
        LocalDate userDate = view.getOrderDateDisplay();
        boolean result = service.checkOrdersByDate(userDate);
        if (result == false) {
            view.displayErrorMessage("There are no Orders from that date.");
        } else {
            List<Order> datedOrderList = service.listOrdersByDate(userDate);
            view.displayListOfOrderDate(datedOrderList);
        }
    }

    private void removeOrder() throws SwgFlooringPersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate removeOrderDate = view.removeOrderDate();
        int removeOrderNumber = view.removeOrderNumber();
        boolean result = service.checkOrders(removeOrderDate, removeOrderNumber);
        if (result == false) {
            view.displayErrorMessage("There are no Orders from that date and order number.");
        } else {
            Order removeOrder = service.getOrder(removeOrderDate, removeOrderNumber);
            view.displayOrderData(removeOrder);

            String userChoice = view.getRemoveConfirmation();
            if (userChoice.equalsIgnoreCase("Y")) {
                service.removeOrder(removeOrderDate, removeOrderNumber);
                view.displayRemoveSuccessBanner();
            } else if (userChoice.equalsIgnoreCase("N")) {
                view.displayOrderNotRemoved();
            } else {
                unknownCommand();
            }
        }
    }

    private void addNewOrder() throws SwgFlooringPersistenceException {

        view.displayAddOrderBanner();
        Order newOrder = view.addNewOrder();

        String state = newOrder.getTax().getState();
        boolean askAgain = true;
        while (askAgain) {

            boolean result = service.checkStateInput(state);
            if (result == false) {
                view.displayErrorMessage("State is invalid.");
                state = view.addState();
            } else {
                Tax tax = service.getTax(state);
                newOrder.setTax(tax);
                askAgain = false;
            }
        }

        String productType = newOrder.getProduct().getProductType();
        boolean askAgain2 = true;
        while (askAgain2) {

            boolean result2 = service.checkProductInput(productType);
            if (result2 == false) {
                view.displayErrorMessage("Product is invalid.");
                productType = view.addProductType();
            } else {
                Product product = service.getProduct(productType);
                newOrder.setProduct(product);
                askAgain2 = false;
            }
        }

        BigDecimal areaOfMaterial = newOrder.getAreaOfMaterial();
        boolean askAgain3 = true;
        while (askAgain3) {
            boolean result3 = service.checkAreaOfMaterial(areaOfMaterial);
            if (result3 == false) {
                view.displayErrorMessage("Area is invalid.");
                String newAreaString = view.addAreaOfMaterial();
                areaOfMaterial = new BigDecimal(newAreaString);
            } else {
                newOrder.setAreaOfMaterial(areaOfMaterial);
                askAgain3 = false;
            }
        }

        int newOrderNumber = service.setOrderNumber(newOrder.getOrderDate());
        newOrder.setOrderNumber(newOrderNumber);

        newOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy"));

        BigDecimal materialCost = service.calculateMaterialCost(newOrder.getProduct(), newOrder.getAreaOfMaterial());
        newOrder.setMaterialCost(materialCost);
        BigDecimal laborCost = service.calculateLaborCost(newOrder.getProduct(), newOrder.getAreaOfMaterial());
        newOrder.setTotalLaborCost(laborCost);

        BigDecimal totalTax = service.calculateTotalTax(materialCost, laborCost, newOrder.getTax());
        BigDecimal totalTax2 = totalTax.setScale(2, RoundingMode.HALF_UP);
        newOrder.setTotalTax(totalTax2);
        BigDecimal totalCost = service.calculateTotalCost(materialCost, laborCost, totalTax);
        BigDecimal totalCost2 = totalCost.setScale(2, RoundingMode.HALF_UP);
        newOrder.setTotalCost(totalCost2);

        view.displaySummary(newOrder);

        String userChoice = view.getConfirmation();
        if (userChoice.equalsIgnoreCase("Y")) {
            service.addNewOrder(newOrder);
            view.displayAddOrderSuccessBanner();
        } else if (userChoice.equalsIgnoreCase("N")) {
            view.displayOrderDiscarded();
        } else {
            unknownCommand();
        }

    }

    private void editAnOrder() throws SwgFlooringPersistenceException {
        LocalDate editOrderDate = view.editOrderDate();
        int editOrderNumber = view.editOrderNumber();
        Order newEditOrder = service.getOrder(editOrderDate, editOrderNumber);
        view.displayEditBanner();
        
        view.displayCurrentName(newEditOrder);
        String editChoiceCustomerName = view.editCustomerName();
        if (editChoiceCustomerName.equalsIgnoreCase("")) {
            //do nothing
        } else if (!editChoiceCustomerName.equals(newEditOrder.getCustomerName())) {
            newEditOrder.setCustomerName(editChoiceCustomerName);
        }

        view.displayCurrentState(newEditOrder);
        boolean askAgain1 = true;
        while (askAgain1) {
            String editChoiceState = view.editState();
            if (editChoiceState.equalsIgnoreCase("")) {
                //do nothing
                askAgain1 = false;
            } else if (!editChoiceState.equals(newEditOrder.getTax().getState())) {
                boolean result1 = service.checkStateInput(editChoiceState);
                if (result1 == false) {
                    view.displayErrorMessage("State is invalid.");
                } else {
                    Tax tax = service.getTax(editChoiceState);
                    newEditOrder.setTax(tax);
                    askAgain1 = false;
                }
            }
        }

        view.displayCurrentProduct(newEditOrder);
        boolean askAgain2 = true;
        while (askAgain2) {
            String editChoiceProductType = view.editProductType();
            if (editChoiceProductType.equalsIgnoreCase("")) {
                //do nothing
                askAgain2 = false;
            } else if (!editChoiceProductType.equals(newEditOrder.getProduct().getProductType())) {
                boolean result2 = service.checkProductInput(editChoiceProductType);
                if (result2 == false) {
                    view.displayErrorMessage("Product Type is invalid.");
                } else {
                    Product product = service.getProduct(editChoiceProductType);
                    newEditOrder.setProduct(product);
                    askAgain2 = false;
                }
            }
        }

        view.displayCurrentArea(newEditOrder);
        boolean askAgain3 = true;
        while (askAgain3) {
            String editChoiceArea = view.editAreaOfMaterial();
            String newAreaString = String.valueOf(newEditOrder.getAreaOfMaterial().doubleValue());
            if (editChoiceArea.equalsIgnoreCase("")) {
                //do nothing
                askAgain3 = false;
            } else if (!editChoiceArea.equals(newAreaString)) {
                BigDecimal newArea = new BigDecimal(editChoiceArea);
                boolean result3 = service.checkAreaOfMaterial(newArea);
                if (result3 == false) {
                    view.displayErrorMessage("Area of Material is invalid.");
                } else {
                    newEditOrder.setAreaOfMaterial(newArea);
                    askAgain3 = false;
                }
            }
        }

        BigDecimal materialCost = service.calculateMaterialCost(newEditOrder.getProduct(), newEditOrder.getAreaOfMaterial());
        newEditOrder.setMaterialCost(materialCost);
        BigDecimal laborCost = service.calculateLaborCost(newEditOrder.getProduct(), newEditOrder.getAreaOfMaterial());
        newEditOrder.setTotalLaborCost(laborCost);

        BigDecimal totalTax = service.calculateTotalTax(materialCost, laborCost, newEditOrder.getTax());
        BigDecimal totalTax2 = totalTax.setScale(2, RoundingMode.HALF_UP);
        newEditOrder.setTotalTax(totalTax2);
        BigDecimal totalCost = service.calculateTotalCost(materialCost, laborCost, totalTax);
        BigDecimal totalCost2 = totalCost.setScale(2, RoundingMode.HALF_UP);
        newEditOrder.setTotalCost(totalCost2);

        view.displayEditSuccessBanner();

    }

    private void saveAllOrders() throws SwgFlooringPersistenceException {
        service.saveAllOrders();
        view.displaySaveSuccessBanner();
    }

}
