/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.swgflooring;

import com.sg.swgflooring.controller.SwgFlooringController;
import com.sg.swgflooring.dao.SwgFlooringAuditDao;
import com.sg.swgflooring.dao.SwgFlooringAuditDaoFileImpl;
import com.sg.swgflooring.dao.SwgFlooringDao;
import com.sg.swgflooring.dao.SwgFlooringDaoFileImpl;
import com.sg.swgflooring.service.SwgFlooringServiceLayer;
import com.sg.swgflooring.service.SwgFlooringServiceLayerImpl;
import com.sg.swgflooring.ui.SwgFlooringView;
import com.sg.swgflooring.ui.UserIO;
import com.sg.swgflooring.ui.UserIOConsoleImpl;

/**
 *
 * @author jakeduerr
 */
public class App {
    
    public static void main(String[] args) {
        
        UserIO myIo = new UserIOConsoleImpl();
        SwgFlooringView myView = new SwgFlooringView(myIo);
        SwgFlooringDao myDao = new SwgFlooringDaoFileImpl();
        SwgFlooringAuditDao myAuditDao = new SwgFlooringAuditDaoFileImpl();
        SwgFlooringServiceLayer myService = new SwgFlooringServiceLayerImpl(myDao, myAuditDao);
        SwgFlooringController controller = new SwgFlooringController(myService, myView);
        controller.run();
        
    }
    
}
