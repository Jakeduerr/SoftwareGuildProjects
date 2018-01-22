/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthyHearts;

import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class HealthyHearts {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("What is your age? ");
        float userAge = sc.nextFloat();

        float maxHeartRate = 220 - userAge;
        float heartRateZoneMin = maxHeartRate * .5f;
        float heartRateZoneMax = maxHeartRate * .85f;
        float hrMin = (float)Math.round(heartRateZoneMin);
        float hrMax = (float)Math.round(heartRateZoneMax);
        float maxHr = (float)Math.round(maxHeartRate);
        
        System.out.println("Your maximum heart rate should be " + maxHr + " beats per minute.");
        System.out.println("Your target HR Zone is " + hrMin + " - " + hrMax + " beats per minute.");

    }
}
