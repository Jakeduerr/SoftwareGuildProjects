/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

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

    }

    private void getChangeDvd() {

        boolean askAgain = true;
        int menuSelection;

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

    }

//    public Dvd getEditDvdInfo() {
//
//        boolean askAgain = true;
//        String title = null;
//        int menuSelection = 0;
//        Dvd currentDvd = new Dvd(title);
//
//        while (askAgain) {
//            view.displayEditDvdBanner();
//            title = view.getEditChoice();
//            Dvd dvd = dao.getDvd(title);
//            view.displayDvd(dvd);
//            menuSelection = getEditMenuSelection();
//
//            switch (menuSelection) {
//                case 1:
//                    String releaseDate = io.readString("Please enter the NEW DVD release date: ");
//                    currentDvd.setReleaseDate(releaseDate);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                case 2:
//                    String mpaaRating = io.readString("Please enter the NEW DVD MPAA rating: ");
//                    currentDvd.setMpaaRating(mpaaRating);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                case 3:
//                    String directorName = io.readString("Please enter the NEW Director's Name: ");
//                    currentDvd.setDirectorName(directorName);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                case 4:
//                    String studio = io.readString("Please enter the NEW Studio's Name: ");
//                    currentDvd.setStudio(studio);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                case 5:
//                    String userRating = io.readString("Please enter your NEW personal rating of the DVD: "
//                            + " (e.g. Hilarious comdedy movie!)");
//                    currentDvd.setUserRating(userRating);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                case 6:
//                    io.print("Exited Edit Menu");
//                    askAgain = false;
//                    break;
//                default:
//                    unknownCommand();
//
//            }
//
//        }
//        return currentDvd;
//    }

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
    private void createDvd() {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }

    //displays all dvds in library with banners
    private void listDvds() {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    //calls dvd choice by title and display its info
    private void viewDvd() {
        view.displayDisplayDvdBanner();
        String title = view.getTitleChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
    }

    private void removeDvd() {
        view.displayRemoveDvdBanner();
        String title = view.getRemoveChoice();
        dao.removeDvd(title);
        view.displayRemoveSuccessBanner();
    }

    private void unknownCommand() {
        view.diplayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

    //method that asks for choice and replaces dvd choice values
    private void editDvd() {
        view.displayEditDvdBanner();
        String title = view.getEditChoice();
        Dvd dvd = dao.getDvd(title);
        view.displayDvd(dvd);
        dao.removeDvd(title);
        view.displayEditDvdBanner();
        Dvd newDvd = view.getEditDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayEditDvdSuccessBanner();

    }
}
