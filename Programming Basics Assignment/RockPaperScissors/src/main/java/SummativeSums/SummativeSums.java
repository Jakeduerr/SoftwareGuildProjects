/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SummativeSums;

/**
 *
 * @author jakeduerr
 */
public class SummativeSums {

    public static void main(String[] args) {

        System.out.println("#1 Array Sum: " + getSum1());
        System.out.println("#2 Array Sum: " + getSum2());
        System.out.println("#3 Array Sum: " + getSum3());

    }

    public static int getSum1() {

        int[] array1 = {1, 90, -33, -55, 67, -16, 28, -55, 15};

        int sumArray1 = 0;

        for (int i = 0; i < array1.length; i++) {

            sumArray1 += array1[i];

        }
        return sumArray1;
    }

    public static int getSum2() {

        int[] array2 = {999, -60, -77, 14, 160, 301};

        int sumArray2 = 0;

        for (int i = 0; i < array2.length; i++) {

            sumArray2 += array2[i];

        }
        return sumArray2;
    }

    public static int getSum3() {

        int[] array3 = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};

        int sumArray3 = 0;

        for (int i = 0; i < array3.length; i++) {

            sumArray3 += array3[i];

        }
        return sumArray3;
    }

}
