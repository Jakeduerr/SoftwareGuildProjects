/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine.dao;

import com.sg.sodamachine.sodamachine.dto.Soda;
import java.io.BufferedReader;
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

    public static final String SODA_FILE = "soda.txt";
    public static final String DELIMITER = "::";

    private Map<String, Soda> sodas = new HashMap<>();

    private void loadSodaMachine() throws SodaMachinePersistenceException {
        Scanner scanner;

        try {

            scanner = new Scanner(new BufferedReader(new FileReader(SODA_FILE)));
        } catch (FileNotFoundException e) {
            throw new SodaMachinePersistenceException(
                    "Could not load roster data into memory.", e);
        }

        String currentLine;
        String[] currentTokens;
        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();

            currentTokens = currentLine.split(DELIMITER);

            Soda currentSoda = new Soda(currentTokens[0]);
            currentTokens[1] = BigDecimal.parseBigDecimal(currentSoda.getSodaCost());
            currentTokens[2] = Integer.parseInt(currentSoda.getNumOfSoda());
            

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
    public Soda addSoda(String sodaName, Soda soda) throws SodaMachinePersistenceException {

        Soda newSoda = sodas.put(sodaName, soda);
        writeSodaMachine();
        return newSoda;
    }

    @Override
    public List<Soda> getAllSoda() throws SodaMachinePersistenceException {
        loadSodaMachine();
        return new ArrayList<Soda>(sodas.values());
    }

    @Override
    public Soda getSoda(String sodaName) throws SodaMachinePersistenceException {
        loadSodaMachine();
        return sodas.get(sodaName);
    }

    @Override
    public Soda removeSoda(Soda sodaName) throws SodaMachinePersistenceException {
        loadSodaMachine();
        Soda removedSoda = sodas.remove(sodaName);
        writeSodaMachine();
        return removedSoda;
    }

    @Override
    public BigDecimal getSodaCost(BigDecimal sodaCost) throws SodaMachinePersistenceException {
        loadSodaMachine();
        return sodaCost;
    }

}
