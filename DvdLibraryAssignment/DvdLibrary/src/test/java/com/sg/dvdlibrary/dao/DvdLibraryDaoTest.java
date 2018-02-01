/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Dvd;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jakeduerr
 */
public class DvdLibraryDaoTest {
    
    private DvdLibraryDao dao = new DvdLibraryDaoFileImpl();
    
    public DvdLibraryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() { 
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        
        List<Dvd> dvdList = dao.getAllDvds();
        
        for (Dvd dvd : dvdList) {
            dao.removeDvd(dvd.getTitle());
        }
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testAddDvd() throws Exception {
        
        Dvd dvd = new Dvd("ham");
        dvd.setReleaseDate(LocalDate.parse("02/23/2003", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd.setMpaaRating("R");
        dvd.setDirectorName("John");
        dvd.setStudio("Sony");
        dvd.setUserRating("bad");
        
        dao.addDvd(dvd.getTitle(), dvd);
        
        Dvd fromDao = dao.getDvd(dvd.getTitle());
        
        assertEquals(dvd, fromDao);
    }

    /**
     * Test of getAllDvds method, of class DvdLibraryDao.
     */
    @Test
    public void testGetAllDvds() throws Exception {
        
        Dvd dvd1 = new Dvd("tod");
        dvd1.setReleaseDate(LocalDate.parse("02/23/2003", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd1.setMpaaRating("G");
        dvd1.setDirectorName("Steve");
        dvd1.setStudio("paramount");
        dvd1.setUserRating("good");
        
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        Dvd dvd2 = new Dvd("ted");
        dvd2.setReleaseDate(LocalDate.parse("02/23/2004", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd2.setMpaaRating("PG");
        dvd2.setDirectorName("Steven");
        dvd2.setStudio("paramount pic");
        dvd2.setUserRating("goodish");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        assertEquals(2, dao.getAllDvds().size());
        
    }

    /**
     * Test of removeDvd method, of class DvdLibraryDao.
     */
    @Test
    public void testRemoveDvd() throws Exception {
        
        Dvd dvd1 = new Dvd("tod");
        dvd1.setReleaseDate(LocalDate.parse("02/23/2003", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd1.setMpaaRating("G");
        dvd1.setDirectorName("Steve");
        dvd1.setStudio("paramount");
        dvd1.setUserRating("good");
        
        dao.addDvd(dvd1.getTitle(), dvd1);
        
        Dvd dvd2 = new Dvd("ted");
        dvd2.setReleaseDate(LocalDate.parse("02/23/2004", DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        dvd2.setMpaaRating("PG");
        dvd2.setDirectorName("Steven");
        dvd2.setStudio("paramount pic");
        dvd2.setUserRating("goodish");
        
        dao.addDvd(dvd2.getTitle(), dvd2);
        
        dao.removeDvd(dvd1.getTitle());
        assertEquals(1, dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd1.getTitle()));
        
        dao.removeDvd(dvd2.getTitle());
        assertEquals(0, dao.getAllDvds().size());
        assertNull(dao.getDvd(dvd2.getTitle()));
        
    }
}
