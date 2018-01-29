/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author jakeduerr
 */
public interface DvdLibraryDao {
    
    //adds dvd to the library
    //if title already exist will return the dvd
    Dvd addDvd(String title, Dvd dvd);
    
    //string array that lists all dvd in library
    List<Dvd> getAllDvds();
    
    //returns the dvd that goes with the given title
    Dvd getDvd(String title);
    
    //removes the dvd that goes with the given title, null if doesn't exist
    Dvd removeDvd(String title);
    
    
}
