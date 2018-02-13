/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

import com.sg.sodamachine.service.SodaMachineUnknownSodaException;
import com.sg.sodamachine.dto.Soda;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class SodaMachineDaoFileImpl implements SodaMachineDao {

    public static final String SODA_FILE = "sodas.txt";
    public static final String DELIMITER = "::";

    private Map<String, Soda> sodas = new HashMap<>();

    private void loadSodaMachine() throws SodaMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(new FileReader(SODA_FILE));
        } catch (FileNotFoundException e) {
            throw new SodaMachinePersistenceException(
                    "File could not load.", e);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Soda currentSoda = new Soda(currentTokens[0]);
            currentSoda.setSodaCost(new BigDecimal(currentTokens[1]));
            currentSoda.setNumOfSoda(Integer.parseInt(currentTokens[2]));

            sodas.put(currentSoda.getSodaName(), currentSoda);
        }

        scanner.close();

    }

    private void writeSodaMachine() throws SodaMachinePersistenceException {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(SODA_FILE));

        } catch (IOException ex) {
            System.out.println("Can't find file: " + ex.getMessage());
        }

        List<Soda> sodaList = new ArrayList<>(sodas.values());
        for (Soda currentSoda : sodaList) {
            out.println(currentSoda.getSodaName() + DELIMITER
                    + currentSoda.getSodaCost() + DELIMITER
                    + currentSoda.getNumOfSoda());

            out.flush();
        }
        out.close();
    }

    @Override
    public List<Soda> getAllSoda() throws SodaMachinePersistenceException {
        loadSodaMachine();
        return new ArrayList<>(sodas.values());
    }

    @Override
    public Soda getSoda(String sodaName) throws SodaMachinePersistenceException {
        loadSodaMachine();
        return sodas.get(sodaName);
    }

    @Override
    public void updateSoda(String sodaName) throws SodaMachinePersistenceException {
        loadSodaMachine();
        int userPick = sodas.get(sodaName).getNumOfSoda();
        userPick--;
        sodas.get(sodaName).setNumOfSoda(userPick);
        writeSodaMachine();
    }

    @Override
    public BigDecimal getSodaCost(String sodaName) throws SodaMachinePersistenceException {
        loadSodaMachine();
        BigDecimal sodaCost = new BigDecimal("0.0");
        for (Soda soda : sodas.values()) {
            if (sodaName.equalsIgnoreCase(soda.getSodaName())) {
                sodaCost = soda.getSodaCost();

            }
        }
        return sodaCost;
    }

    @Override
    public void stockSoda() throws SodaMachinePersistenceException {
        loadSodaMachine();
        List<Soda> sodaList = new ArrayList<>(sodas.values());
        sodaList.forEach((soda) -> {
            soda.setNumOfSoda(2);
        });
        writeSodaMachine();
    }

}
