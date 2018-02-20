/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.controller;

import com.sg.swgflooring.dao.SwgFlooringDao;
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
        try {

            while (askAgain) {
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        displayOrdersByDate();
                        break;
                    case 2:
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
            }
            exitMessage();
        } catch (SwgFlooringPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
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
                io.print("Order was NOT removed.");
            } else {
                unknownCommand();
            }
        }
    }

    private void addNewOrder() throws SwgFlooringPersistenceException {

        view.displayAddOrderBanner();
//        List<Product> productList = service.getProductsList();
//        view.displayListOfProducts(productList);
//        List<Tax> stateList = service.getTaxesList();
//        view.displayListOfStates(stateList);
        Order newOrder = view.addNewOrder();
        String state = newOrder.getTax().getState();
        boolean result1 = service.checkStateInput(state);
        String productType = newOrder.getProduct().getProductType();
        boolean result2 = service.checkProductInput(productType);
        BigDecimal areaOfMaterial = newOrder.getAreaOfMaterial();
        boolean result3 = service.checkAreaOfMaterial(areaOfMaterial);

        if (result1 == false || result2 == false || result3 == false) {
            view.displayErrorMessage("State, Product, or Area are invalid.");
        } else {
            
            int newOrderNumber = service.setOrderNumber(newOrder.getOrderDate());
            newOrder.setOrderNumber(newOrderNumber);

            newOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MMddyyyy"));

            Product product = service.getProduct(productType);
            newOrder.setProduct(product);

            BigDecimal materialCost = service.calculateMaterialCost(product, newOrder.getAreaOfMaterial());
            newOrder.setMaterialCost(materialCost);
            BigDecimal laborCost = service.calculateLaborCost(product, newOrder.getAreaOfMaterial());
            newOrder.setTotalLaborCost(laborCost);

            Tax tax = service.getTax(state);
            newOrder.setTax(tax);

            BigDecimal totalTax = service.calculateTotalTax(materialCost, laborCost, tax);
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
                io.print("Order has been discarded.");
            } else {
                unknownCommand();
            }
        }

    }

    private void editAnOrder() throws SwgFlooringPersistenceException {
        view.displayEditBanner();
        LocalDate editOrderDate = view.editOrderDate();
        int editOrderNumber = view.editOrderNumber();
        Order newEditOrder = service.getOrder(editOrderDate, editOrderNumber);

        io.print("Current Customer Name: " + newEditOrder.getCustomerName());
        String editChoiceCustomerName = view.editCustomerName();
        if (editChoiceCustomerName.equalsIgnoreCase("")) {
            //do nothing
        } else if (!editChoiceCustomerName.equals(newEditOrder.getCustomerName())) {
            newEditOrder.setCustomerName(editChoiceCustomerName);
        }

        io.print("Current Customer State: " + newEditOrder.getTax().getState());
        String editChoiceState = view.editState();
        if (editChoiceState.equalsIgnoreCase("")) {
            //do nothing
        } else if (!editChoiceState.equals(newEditOrder.getTax().getState())) {
            newEditOrder.getTax().setState(editChoiceState);
        }

        io.print("Current Product Type: " + newEditOrder.getProduct().getProductType());
        String editChoiceProductType = view.editProductType();
        if (editChoiceProductType.equalsIgnoreCase("")) {
            //do nothing
        } else if (!editChoiceProductType.equals(newEditOrder.getProduct().getProductType())) {
            newEditOrder.getProduct().setProductType(editChoiceProductType);
        }

        io.print("Current Area of Material: " + newEditOrder.getAreaOfMaterial());

        String editChoiceArea = view.editAreaOfMaterial();
        String newAreaString = String.valueOf(newEditOrder.getAreaOfMaterial().doubleValue());
        if (editChoiceArea.equalsIgnoreCase("")) {
            //do nothing
        } else if (!editChoiceArea.equals(newAreaString)) {
            BigDecimal newArea = new BigDecimal(editChoiceArea);
            newEditOrder.setAreaOfMaterial(newArea);
        }

        String productType = newEditOrder.getProduct().getProductType();
        Product product = service.getProduct(productType);
        newEditOrder.setProduct(product);
        BigDecimal materialCost = service.calculateMaterialCost(product, newEditOrder.getAreaOfMaterial());
        newEditOrder.setMaterialCost(materialCost);
        BigDecimal laborCost = service.calculateLaborCost(product, newEditOrder.getAreaOfMaterial());
        newEditOrder.setTotalLaborCost(laborCost);

        String state = newEditOrder.getTax().getState();
        Tax tax = service.getTax(state);
        newEditOrder.setTax(tax);

        BigDecimal totalTax = service.calculateTotalTax(materialCost, laborCost, tax);
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
