/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.ui;

import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringView {

    UserIO io;

    public SwgFlooringView(UserIO io) {
        this.io = io;
    }

    public int displayMenuAndGetSelection() {

        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");
        io.print("* ==== Flooring Program ====");
        io.print("* ");
        io.print("* 1. Display Orders");
        io.print("* 2. Add an Order");
        io.print("* 3. Edit an Order");
        io.print("* 4. Remove an Order");
        io.print("* 5. Save Current Work");
        io.print("* 6. Exit");
        io.print("* ");
        io.print("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * ");

        return io.readInt("Please select from the choices ABOVE", 1, 6);

    }

    public void displayOrdersBanner() {
        io.print("==== Orders By Date ====");
    }

    public void displayOrderSuccessBanner() {
        io.print("==== Order Successfully Added ====");
    }

    public void displayEditBanner() {
        io.print("==== Edit your Order ====");
    }

    public void diplayEditSuccessBanner() {
        io.print("==== Order Successfully Edited ====");
    }

    public void displaySaveSuccessBanner() {
        io.print("==== Work Successfully Saved ====");
    }

    public void displayAddOrderBanner() {
        io.print("==== Add an Order ====");
    }
    
    public void displayRemoveOrderBanner() {
        io.print("==== Remove an Order ====");
    }
    
    public void displayRemoveSuccessBanner() {
        io.print("==== Successfully Removed an Order ====");
    }
    
    public void displayExitBanner() {
        io.print("Thank you and have a great day!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command! Please enter a valid command.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("!!! ERROR !!!");
        io.print(errorMsg);
    }
    
    public void enterToContinue() {
        io.readString("Please press enter to continue.");
    }

    public String getConfirmation() {
        String userConfirm = io.readString("Enter Y: for YES and N: for NO");
        return userConfirm;
    }
    
    public LocalDate getOrderDateDisplay() {
        String userInput = io.readString("Please enter the date of the Orders you wish to view, Date Format: MMDDYYYY");
        LocalDate userEntry = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MMddyyyy"));
        return userEntry;
    }

    public void displayListOfOrderDate(List<Order> orders) {
        io.print("^^Orders by date above^^ ");
        io.print(" ");
        for (Order order : orders) {

            io.print("Order Date: " + order.getOrderDate());
            io.print("Order Number: " + order.getOrderNumber());
            io.print("Customer Name: " + order.getCustomerName());
            io.print("State: " + order.getTax().getState());
            io.print("Tax Rate: " + order.getTax().getTaxRate());
            io.print("Product Type: " + order.getProduct().getProductType());
            io.print("Area of Material: " + order.getAreaOfMaterial());
            io.print("Cost per Square Foot: " + order.getProduct().getCostPerSquareFoot());
            io.print("Labor Cost per Square Foot: " + order.getProduct().getLaborCostPerSquareFoot());
            io.print("Material Cost: " + order.getMaterialCost());
            io.print("Labor Cost: " + order.getTotalLaborCost());
            io.print("Tax: " + order.getTotalTax());
            io.print("Total: " + order.getTotalCost());
            io.print(" ");
        }

    }

    public Order addNewOrder() {
        String customerName = io.readString("Please enter your name: ");
        String state = io.readString("Please enter the State: ");
        String productType = io.readString("Please enter the type of material: ");
        String areaOfMaterial = io.readString("Please enter the required area of material: ");
        BigDecimal desiredArea = new BigDecimal(areaOfMaterial);

        Order currentOrder = new Order(LocalDate.now());
        currentOrder.setCustomerName(customerName);
        
        Tax tax = new Tax();
        tax.setState(state);
        currentOrder.setTax(tax);

        Product product = new Product();
        product.setProductType(productType);
        currentOrder.setProduct(product);
        
        currentOrder.setAreaOfMaterial(desiredArea);

        return currentOrder;
    }
    
    public LocalDate editOrderDate() {
        String userInput = io.readString("Please enter the date of the Order you wish to edit, Date Format: MMDDYYYY");
        LocalDate userEntry = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MMddyyyy"));
        return userEntry;
    }
    
    public int editOrderNumber() {
        int userChoice = io.readInt("Please enter the Order Number of the Order you wish to edit: ");
        return userChoice;
        
    }
    
    public LocalDate removeOrderDate() {
        String userInput = io.readString("Please enter the date of the Orders you wish to remove, Date Format: MMDDYYYY");
        LocalDate userEntry = LocalDate.parse(userInput, DateTimeFormatter.ofPattern("MMddyyyy"));
        return userEntry;
    }
    
    public int removeOrderNumber() {
        int userChoice = io.readInt("Please enter the Order Number of the Order you wish to remove: ");
        return userChoice;
        
    }


    

   

}
