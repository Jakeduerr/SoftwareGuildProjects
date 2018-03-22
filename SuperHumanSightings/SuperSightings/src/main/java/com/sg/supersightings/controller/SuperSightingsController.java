/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SuperSightingsDao;
import javax.inject.Inject;

/**
 *
 * @author jakeduerr
 */
public class SuperSightingsController {
    
    SuperSightingsDao dao;
    
    @Inject
    public SuperSightingsController(SuperSightingsDao dao) {
        this.dao = dao;
    }
    
    
    
    
    
}
