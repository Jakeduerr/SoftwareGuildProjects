/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog;

import Dog.Dog;

/**
 *
 * @author jakeduerr
 */
public class Lab extends Dog {
    
    public Lab(String name) {
        super(name);
        
    }

    @Override
    public void bark() {
        super.bark(); //To change body of generated methods, choose Tools | Templates.
        System.out.println(getName() + " says Ruff!");
    }
    
    
    
}
