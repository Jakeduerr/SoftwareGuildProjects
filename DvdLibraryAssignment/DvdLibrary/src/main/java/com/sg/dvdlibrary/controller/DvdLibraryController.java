/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.DvdLibraryDaoException;
import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.ui.UserIO;
import com.sg.dvdlibrary.ui.UserIOConsoleImpl;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class DvdLibraryController {

    DvdLibraryDao dao;
    DvdLibraryView view;
    private UserIO io = new UserIOConsoleImpl();

    //DI_constructor that initializes dao and view
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }

    //runs the selection and loop of the menu system
    public void run() {

        boolean askAgain = true;
        int menuSelection;
        try {

            while (askAgain) {
                //calling menu method
                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        getChangeDvd();
                        break;
                    case 7:
                        askAgain = false;
                        break;
                    default:
                        unknownCommand();

                }
            }
            exitMessage();
        } catch (DvdLibraryDaoException ex) {
            view.diplayErrorMessage(ex.getMessage());
        }
    }

    //runs selection and the loop of the change menu
    private void getChangeDvd() {

        boolean askAgain = true;
        int menuSelection;
        try {

            while (askAgain) {
                //calling menu method
                menuSelection = getChangeMenuSelection();

                switch (menuSelection) {

                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        askAgain = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
        } catch (DvdLibraryDaoException ex) {
            view.diplayErrorMessage(ex.getMessage());
        }
    }

    //calling menu selection method from view
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private int getChangeMenuSelection() {
        return view.printChangeMenuAndGetSelection();
    }

//    private int getEditMenuSelection() {
//        return view.printMenuAndGetEditSelection();
//    }
    //creates new dvd and takes in info from view and adds new dvd to library
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    //displays all dvds in library with banners
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    //calls dvd choice by title and display its info
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        checkDvdTitle();

    }

    private void unknownCommand() {
        view.diplayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    //method that asks for choice and replaces dvd choice values
    private void editDvd() throws DvdLibraryDaoException {
        view.displayEditDvdBanner();
        checkDvdEditTitle();

    }

    private void checkDvdTitle() throws DvdLibraryDaoException {
        String title = view.getRemoveChoice();
        Dvd dvd = dao.getDvd(title);
        if (dvd == null) {
            System.out.println("Dvd does not exist in Library.");
        } else {
            dao.removeDvd(title);
            view.displayRemoveSuccessBanner();
        }

    }

    private void checkDvdEditTitle() throws DvdLibraryDaoException {
        String title = view.getEditChoice();
        Dvd dvd = dao.getDvd(title);
        if (dvd == null) {
            System.out.println("Dvd does not exist in Library.");
        } else {
            view.displayEditDvdBanner();
            dao.removeDvd(title);
            Dvd newDvd = view.getEditDvdInfo();
            dao.addDvd(newDvd.getTitle(), newDvd);
            view.displayEditDvdSuccessBanner();
        }

    }
}
