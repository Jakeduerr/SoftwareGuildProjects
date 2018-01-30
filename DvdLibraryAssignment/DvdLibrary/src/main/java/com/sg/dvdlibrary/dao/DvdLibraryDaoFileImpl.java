/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvds = new HashMap<>();
    public static final String LIBRARY_FILE = "library.txt";
    public static final String DELIMITER = "::";

    //getting new dvd title and putting it in new hashmap called dvds
    @Override
    public Dvd addDvd(String title, Dvd dvd) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd newDvd = dvds.put(title, dvd);
        writeLibrary();
        return newDvd;
    }

    //getting entire list of existing dvds and creating a new Dvd ArrayList but can be
    //treated as a list cuz it implements DvdLibrary
    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException {
        loadLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        return dvds.get(title);
    }

    @Override
    public Dvd removeDvd(String title) throws DvdLibraryDaoException {
        loadLibrary();
        Dvd removedDvd = dvds.remove(title);
        writeLibrary();
        return removedDvd;
    }

    //method that loads all data from dvd and splits it into delimiter text
    private void loadLibrary() throws DvdLibraryDaoException {
        Scanner sc = null;
        try {
            sc = new Scanner(new BufferedReader(new FileReader(LIBRARY_FILE)));

        } catch (FileNotFoundException ex) {
            throw new DvdLibraryDaoException("File Not Found", ex);
        }

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] lineParts = currentLine.split(DELIMITER);
            Dvd currentDvd = new Dvd(lineParts[0]);
            currentDvd.setReleaseDate(lineParts[1]);
            currentDvd.setMpaaRating(lineParts[2]);
            currentDvd.setDirectorName(lineParts[3]);
            currentDvd.setStudio(lineParts[4]);
            currentDvd.setUserRating(lineParts[5]);
            dvds.put(currentDvd.getTitle(), currentDvd);

        }
        sc.close();
    }

    //method that writes all the data from a dvd onto a text file
    private void writeLibrary() throws DvdLibraryDaoException {
        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(LIBRARY_FILE));

        } catch (IOException ex) {
            System.out.println("Can't find file: " + ex.getMessage());
        }

        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMpaaRating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserRating());
            out.flush();
        }
        out.close();

    }
}
