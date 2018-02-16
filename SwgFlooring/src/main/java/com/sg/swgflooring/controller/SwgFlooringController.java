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
import java.time.LocalDate;
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
                        io.print("3. Edit an Order");
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        io.print("5. Save Current Work");
                        break;
                    case 6:
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
        List<Order> datedOrderList = service.listOrdersByDate(userDate);
        view.displayListOfOrderDate(datedOrderList);

    }
    
    private void removeOrder() throws SwgFlooringPersistenceException {
        view.displayRemoveOrderBanner();
        LocalDate removeOrderDate = view.removeOrderDate();
        int removeOrderNumber = view.removeOrderNumber();
        service.removeOrder(removeOrderDate, removeOrderNumber);
        view.displayRemoveSuccessBanner();
    }
    
    private void addNewOrder() throws SwgFlooringPersistenceException {
        view.displayAddOrderBanner();
        Order newOrder = view.addNewOrder();
        
        String productType = newOrder.getProduct().getProductType();
        Product product = service.getProduct(productType);
        newOrder.setProduct(product);
        BigDecimal materialCost = service.calculateMaterialCost(product, newOrder.getAreaOfMaterial());
        newOrder.setMaterialCost(materialCost);
        BigDecimal laborCost = service.calculateLaborCost(product, newOrder.getAreaOfMaterial());
        newOrder.setTotalLaborCost(laborCost);
        
        String state = newOrder.getTax().getState();
        Tax tax = service.getTax(state);
        newOrder.setTax(tax);
        
        BigDecimal totalTax = service.calculateTotalTax(materialCost, laborCost, tax);
        BigDecimal totalCost = service.calculateTotalCost(materialCost, laborCost, totalTax);
        newOrder.setTotalCost(totalCost);
        
        
        
    }
    
    
    
    private BigDecimal getUserArea() {
        Order newOrder = view.addNewOrder();
        BigDecimal userArea = newOrder.getAreaOfMaterial();
        return userArea;
    }
    
    private void getUserMaterial() throws SwgFlooringPersistenceException {
        Order newOrder = view.addNewOrder();
        String userMaterial = newOrder.getProduct().getProductType();
        BigDecimal materialCost = service.getProductTypeAndSetCost(userMaterial);
        
    }
}
