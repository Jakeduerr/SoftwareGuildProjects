/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring.dao;

import com.sg.swgflooring.dto.Order;
import com.sg.swgflooring.dto.Product;
import com.sg.swgflooring.dto.Tax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author jakeduerr
 */
public class SwgFlooringDaoFileImpl implements SwgFlooringDao {

    public static final String TAX_FILE = "Taxes.txt";
    public static final String PRODUCT_FILE = "Products.txt";
    public static final String DELIMITER = ",";
    private List<Order> orders = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private List<Tax> taxes = new ArrayList<>();
    private List<LocalDate> orderDateList = new ArrayList<>();

    private void loadSwgFlooring(LocalDate orderDate) throws SwgFlooringPersistenceException {

        List<LocalDate> allOrderDates = listAllDates();
        for (LocalDate localDate : allOrderDates) {
            if (orderDate.equals(localDate)) {
                return;
            }
        }

        Scanner scanner;
        String orderDateFile = "Orders_" + (orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"))) + ".txt";
        try {
            scanner = new Scanner(new FileReader(orderDateFile));
        } catch (FileNotFoundException ex) {
            throw new SwgFlooringPersistenceException("File could not load.", ex);
        }

        while (scanner.hasNextLine()) {

            String currentLine = scanner.nextLine();

            String[] currentTokens = currentLine.split(DELIMITER);

            Order currentOrder = new Order(orderDate);

            currentOrder.setOrderNumber(Integer.parseInt(currentTokens[0]));
            currentOrder.setCustomerName(currentTokens[1]);

            Tax tax = new Tax();
            tax.setState((currentTokens[2]));
            tax.setTaxRate(new BigDecimal(currentTokens[3]));
            currentOrder.setTax(tax);

            Product product = new Product();
            product.setProductType(currentTokens[4]);

            currentOrder.setAreaOfMaterial(new BigDecimal(currentTokens[5]));

            product.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
            product.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
            currentOrder.setProduct(product);

            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
            currentOrder.setTotalLaborCost(new BigDecimal(currentTokens[9]));
            currentOrder.setTotalTax(new BigDecimal(currentTokens[10]));
            currentOrder.setTotalCost(new BigDecimal(currentTokens[11]));

            orders.add(currentOrder);

        }

        scanner.close();

    }

    private void loadSwgFlooringProduct() throws SwgFlooringPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader("Products.txt"));
        } catch (FileNotFoundException ex) {
            throw new SwgFlooringPersistenceException(
                    "File could not load.", ex);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product();
            currentProduct.setProductType(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.add(currentProduct);
        }

        scanner.close();

    }

    private void loadSwgFlooringTaxes() throws SwgFlooringPersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader("Taxes.txt"));
        } catch (FileNotFoundException ex) {
            throw new SwgFlooringPersistenceException(
                    "File could not load.", ex);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Tax currentTax = new Tax();
            currentTax.setState(currentTokens[0]);
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            taxes.add(currentTax);
        }

        scanner.close();

    }

    private void writeSwgFlooring(LocalDate writeOrderDate) throws SwgFlooringPersistenceException {

        List<Order> newList = listOrdersByDate(writeOrderDate);

        String writeOrderDateFile = "Orders_" + (writeOrderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"))) + ".txt";

        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(writeOrderDateFile));

        } catch (IOException ex) {
            System.out.println("Can't find file: " + ex.getMessage());
        }

        for (Order currentOrder : newList) {
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getTax().getState() + DELIMITER
                    + currentOrder.getTax().getTaxRate() + DELIMITER
                    + currentOrder.getProduct().getProductType() + DELIMITER
                    + currentOrder.getAreaOfMaterial() + DELIMITER
                    + currentOrder.getProduct().getCostPerSquareFoot() + DELIMITER
                    + currentOrder.getProduct().getLaborCostPerSquareFoot() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getTotalLaborCost() + DELIMITER
                    + currentOrder.getTotalTax() + DELIMITER
                    + currentOrder.getTotalCost());

            out.flush();
        }
        out.close();
    }

    @Override
    public List<Product> getProductsList() throws SwgFlooringPersistenceException {
        loadSwgFlooringProduct();
        return products;
    }

    @Override
    public Product getProduct(String productType) throws SwgFlooringPersistenceException {
        loadSwgFlooringProduct();
        for (Product product : products) {
            if (productType.equalsIgnoreCase(product.getProductType())) {
                return product;
            }

        }
        return null;

    }

    @Override
    public List<Tax> getTaxesList() throws SwgFlooringPersistenceException {
        loadSwgFlooringTaxes();
        return taxes;
    }

    @Override
    public Tax getTax(String state) throws SwgFlooringPersistenceException {
        loadSwgFlooringTaxes();
        Tax tax = taxes.stream()
                .filter(t -> t.getState().equalsIgnoreCase(state))
                .findFirst().orElse(null);

        return tax;

    }

    @Override
    public int setOrderNumber(LocalDate orderDate) {
        int newOrderNumber;
        try {
            loadSwgFlooring(orderDate);
        } catch (SwgFlooringPersistenceException ex) {
            return 1;
        }
        Order orderWithMaxNum = orders.stream()
                .max(Comparator.comparing(Order::getOrderNumber))
                .get();
        newOrderNumber = orderWithMaxNum.getOrderNumber() + 1;

        return newOrderNumber;

    }

    @Override
    public List<LocalDate> listAllDates() throws SwgFlooringPersistenceException {
        List<LocalDate> dateList = new ArrayList<>();
        for (Order order : orders) {
            dateList.add(order.getOrderDate());

        }
        return dateList;
    }

    @Override
    public void addNewOrder(Order order) throws SwgFlooringPersistenceException {
        orders.add(order);
    }

    @Override
    public void saveOrder(List<LocalDate> orderDateList) throws SwgFlooringPersistenceException {
        for (LocalDate localDate : orderDateList) {
            writeSwgFlooring(localDate);
        }

    }

    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws SwgFlooringPersistenceException {
        loadSwgFlooring(orderDate);
        List<Order> orderList = new ArrayList<>();
        for (Order order : orders) {

            if (!order.getOrderDate().equals(orderDate)) {
                return null;
                
            } else {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        loadSwgFlooring(orderDate);
        for (Order order : orders) {

            if (order.getOrderDate().equals(orderDate) && order.getOrderNumber() == (orderNumber)) {
                return order;
            }
        }
        return null;

    }

    @Override
    public void removeOrder(LocalDate orderDate, int orderNumber) throws SwgFlooringPersistenceException {
        loadSwgFlooring(orderDate);
        Iterator<Order> itr = orders.iterator();
        while (itr.hasNext()) {
            Order order = itr.next();
            if (order.getOrderDate().equals(orderDate) && order.getOrderNumber() == (orderNumber)) {
                itr.remove();
            }
        }
    }
}
