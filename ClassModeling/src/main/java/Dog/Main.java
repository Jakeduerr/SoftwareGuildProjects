/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dog;

import Dog.Lab;
import Dog.Dog;

/**
 *
 * @author jakeduerr
 */
public class Main {
    
    public static void main(String[] args) {
        
        
//        Dog d = new Dog("Fido");
//        d.bark();
//        
//        Lab l = new Lab("Beast");
//        l.bark();
//        
//        Dog otherDog = new Lab("Sadie");
//        otherDog.bark();
//        
//        System.out.println(d);
//        System.out.println(l);
//        System.out.println(otherDog);
        
        Dog[] dogs = new Dog[4];
        
        dogs[0] = new Lab(" kyle");
        dogs[1] = new Terrier(" Laura");
        dogs[2] = new Terrier(" Corbin");
        dogs[3] = new Lab(" Josh");
        
        for(Dog d : dogs) {
            d.bark();
        }
        
    }
    
    
    
}
