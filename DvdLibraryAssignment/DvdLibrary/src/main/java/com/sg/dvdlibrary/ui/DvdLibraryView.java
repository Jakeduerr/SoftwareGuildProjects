/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public class DvdLibraryView {

    private UserIO io;

    //DI_constuctor that intializes io
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    //coms with controller to interact with user
    public int printMenuAndGetSelection() {

        io.print("DVD Library Menu");
        io.print("1. Add DVD to Library");
        io.print("2. Remove DVD from Library");
        io.print("3. Edit a DVD");
        io.print("4. List DVDs in Library");
        io.print("5. Search for Information on a DVD by Title");
        io.print("6. Enter Change Menu: To ADD, REMOVE, and EDIT DVDs");
        io.print("7. Exit");

        return io.readInt("Please select from the choices ^ABOVE^", 1, 7);
    }

    //asks user for info about dvd and places it in dvd object and value
    public Dvd getNewDvdInfo() {
        String title = io.readString("Please enter the DVD title: ");
        String releaseDate = io.readString("Please enter the DVD release date (mm/dd/yyyy): ");
        String mpaaRating = io.readString("Please enter the DVD MPAA rating: ");
        String directorName = io.readString("Please enter the Director's Name: ");
        String studio = io.readString("Please enter the Studio's Name: ");
        String userRating = io.readString("Please enter your own personal rating of the DVD: "
                + " (e.g. Hilarious comdedy movie!)");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    //displays banner for prompt user to create
    public void displayCreateDvdBanner() {
        io.print(">>> Create a DVD <<<");
    }

    //displays banner for completed creation
    public void displayCreateSuccessBanner() {
        io.readString(">>> CONGRATS! You've created a DVD! <<<  Please press enter to continue.");
    }

    //displays all dvd info in a list
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print("Tile: " + currentDvd.getTitle()
                    + "  Release Date: " + currentDvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"))
                    + "  MPAA Rating: " + currentDvd.getMpaaRating()
                    + "  Director: " + currentDvd.getDirectorName()
                    + "  Studio: " + currentDvd.getStudio()
                    + "  Personal Rating: " + currentDvd.getUserRating());
        }
        io.readString("Please press enter to continue.");
    }

    public void displayDisplayAllBanner() {
        io.print(">>> Display All DVDs <<<");
    }

    public void displayDisplayDvdBanner() {
        io.print(">>> Info on a DVD <<<");
    }

    //promting user to select title and getting that title 
    public String getTitleChoice() {
        return io.readString("Please enter the Title of the DVD you're looking for: ");
    }

    //displays the chosen dvd if it exists in library   
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print("Tile: " + dvd.getTitle());
            io.print("Release Date: " + dvd.getReleaseDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            io.print("MPAA Rating: " + dvd.getMpaaRating());
            io.print("Director: " + dvd.getDirectorName());
            io.print("Studio: " + dvd.getStudio());
            io.print("Personal Rating: " + dvd.getUserRating());
        } else {
            io.print("DVD does not exist in Library.");
        }
        io.readString("Please press enter to continue.");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("!!== Remove DVD ==!!");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("=DVD successfully removed=  Please press enter to continue.");
    }

    public String getRemoveChoice() {
        return io.readString("Please enter the Title of the DVD you want to Remove:");
    }

    public void displayExitBanner() {
        io.print("Good Bye and Thank You for Vistiing the DVD Library!");
    }

    public void diplayUnknownCommandBanner() {
        io.print("UNKNOWN COMMAND!! Please type in a valid command.");
    }

    public void displayEditDvdBanner() {
        io.print(">>> Edit DVD <<<");
    }

    public void displayEditDvdSuccessBanner() {
        io.readString(">>> Congrats! You've successfully edited your DVD! <<<  Please press enter to continue.");
    }

    public String getEditChoice() {
        return io.readString("Please enter the Title of the DVD you wish to Edit: ");
    }

    public Dvd getEditDvdInfo() {
        String title = io.readString("Please enter the NEW DVD title: ");
        String releaseDate = io.readString("Please enter the NEW DVD release date (mm/dd/yyyy): ");
        String mpaaRating = io.readString("Please enter the NEW DVD MPAA rating: ");
        String directorName = io.readString("Please enter the NEW Director's Name: ");
        String studio = io.readString("Please enter the NEW Studio's Name: ");
        String userRating = io.readString("Please enter your NEW personal rating of the DVD: "
                + " (e.g. Hilarious comdedy movie!)");
        Dvd currentDvd = new Dvd(title);
        currentDvd.setReleaseDate(LocalDate.parse(releaseDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        currentDvd.setMpaaRating(mpaaRating);
        currentDvd.setDirectorName(directorName);
        currentDvd.setStudio(studio);
        currentDvd.setUserRating(userRating);
        return currentDvd;
    }

    public void diplayErrorMessage(String errorMsg) {
        io.print("!! ERROR !!!");
        io.print(errorMsg);
    }

    public int printChangeMenuAndGetSelection() {
        io.print("What would you like to do? ");
        io.print("1. Add DVD");
        io.print("2. Remove DVD");
        io.print("3. Edit DVD");
        io.print("4. Exit Change Menu");

        return io.readInt("Please select from the choices ^ABOVE^ ", 1, 4);
    }
}



//    public int printMenuAndGetEditSelection() {
//        io.print("What would you like to edit? ");
//        io.print("1. Release Date");
//        io.print("2. MPAA Rating");
//        io.print("3. Director's Name");
//        io.print("4. Studio's Name");
//        io.print("5. Personal Rating");
//        io.print("6. Exit Edit Menu");
//
//        return io.readInt("Please select from the choices ^ABOVE^ ", 1, 6);
//    }
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
//            if(menuSelection) {
//                
//                    String title = io.readString("Would you like to Edit your DVD title: ");
//                    currentDvd.setReleaseDate(releaseDate);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
//                    String releaseDate = io.readString("Would you like to Edit DVD release date: ");
//                    currentDvd.setReleaseDate(releaseDate);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
//                    String mpaaRating = io.readString("Please enter the NEW DVD MPAA rating: ");
//                    currentDvd.setMpaaRating(mpaaRating);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
//                    String directorName = io.readString("Please enter the NEW Director's Name: ");
//                    currentDvd.setDirectorName(directorName);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
//                    String studio = io.readString("Please enter the NEW Studio's Name: ");
//                    currentDvd.setStudio(studio);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
//                    String userRating = io.readString("Please enter your NEW personal rating of the DVD: "
//                            + " (e.g. Hilarious comdedy movie!)");
//                    currentDvd.setUserRating(userRating);
//                    dao.addDvd(title, currentDvd);
//                    break;
//                
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

