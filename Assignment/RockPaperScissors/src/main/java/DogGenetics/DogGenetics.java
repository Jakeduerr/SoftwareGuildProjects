/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DogGenetics;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class DogGenetics {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rGen = new Random();

        System.out.println("What is your dog's name?");
        String dogName = sc.nextLine();
        
        System.out.println("Well then, I have highly reliable report on " + dogName + "'s prestigious background right here.");

        int sum = 0;
        int dogBreed1 = 0;
        int dogBreed2 = 0;
        int dogBreed3 = 0;
        int dogBreed4 = 0;
        int dogBreed5 = 0;

        while (sum != 100) {

            dogBreed1 = rGen.nextInt(96) + 1;
            dogBreed2 = rGen.nextInt(96) + 1;
            dogBreed3 = rGen.nextInt(96) + 1;
            dogBreed4 = rGen.nextInt(96) + 1;
            dogBreed5 = rGen.nextInt(96) + 1;

            sum = dogBreed1 + dogBreed2 + dogBreed3 + dogBreed4 + dogBreed5;
        }

        System.out.println(dogName + " is: ");
        System.out.println(" ");
        System.out.println(dogBreed1 + "%" + " German Shepard");
        System.out.println(dogBreed2 + "%" + " Poodle");
        System.out.println(dogBreed3 + "%" + " Great Dane");
        System.out.println(dogBreed4 + "%" + " Boxer");
        System.out.println(dogBreed5 + "%" + " Rottweiler");
        System.out.println(" ");
        System.out.println("Wow, that's QUITE a dog!");

    }
}
