/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        String doubleString = sc.nextLine();
        double number = Double.parseDouble(doubleString);
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double double1;

        do {
            System.out.println(prompt);
            String doubleString = sc.nextLine();
            double1 = Double.parseDouble(doubleString);

        } while (double1 > max || double1 < min);

        return double1;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        String floatString = sc.nextLine();
        float number = Float.parseFloat(floatString);
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float float1;

        do {
            System.out.println(prompt);
            String floatString = sc.nextLine();
            float1 = Float.parseFloat(floatString);

        } while (float1 > max || float1 < min);

        return float1;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        String intString = sc.nextLine();
        int integer = Integer.parseInt(intString);
        return integer;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int integer;

        do {
            System.out.println(prompt);
            String intString = sc.nextLine();
            integer = Integer.parseInt(intString);

        } while (integer > max || integer < min);

        return integer;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        String longString = sc.nextLine();
        long number = Long.parseLong(longString);
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long long1;

        do {
            System.out.println(prompt);
            String longString = sc.nextLine();
            long1 = Long.parseLong(longString);

        } while (long1 > max || long1 < min);

        return long1;
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        String response = sc.nextLine();
        return response;
    }

}
