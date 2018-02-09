/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.sodamachine;

import com.sg.sodamachine.controller.SodaMachineController;
import com.sg.sodamachine.dao.SodaMachineDao;
import com.sg.sodamachine.dao.SodaMachineDaoFileImpl;
import com.sg.sodamachine.service.SodaMachineServiceLayer;
import com.sg.sodamachine.service.SodaMachineServiceLayerImpl;
import com.sg.sodamachine.ui.SodaMachineView;
import com.sg.sodamachine.ui.UserIO;
import com.sg.sodamachine.ui.UserIOConsoleImpl;

/**
 *
 * @author jakeduerr
 */
public class App {

    public static void main(String[] args) {

        UserIO myIo = new UserIOConsoleImpl();
        SodaMachineView myView = new SodaMachineView(myIo);
        SodaMachineDao myDao = new SodaMachineDaoFileImpl();
        SodaMachineServiceLayer myService = new SodaMachineServiceLayerImpl(myDao);
        SodaMachineController controller = new SodaMachineController(myService, myView);
        controller.run();

    }

}
