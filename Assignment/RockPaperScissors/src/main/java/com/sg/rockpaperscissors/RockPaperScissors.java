/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.rockpaperscissors;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jakeduerr
 */
public class RockPaperScissors {

    public static void main(String[] args) {

        while (true) {

            Scanner sc = new Scanner(System.in);
            Random rGen = new Random();

            System.out.println("Hello, how many rounds of Rock, Paper, Scissors would you like to play? ");
            int userRounds = sc.nextInt();

            if (userRounds > 10 || userRounds < 1) {
                System.out.println("Error: Amount of rounds must be between 1 and 10.");
                System.exit(0);
            }

            int totalTies = 0;
            int userWins = 0;
            int compWins = 0;

            for (int i = 0; i < userRounds; i++) {

                System.out.println("Please choose 1 for rock, 2 for paper, or 3 for scissors: ");
                int userPick = sc.nextInt();

                int compPick = rGen.nextInt(3) + 1;

                if (userPick == 1 && compPick == 3) {
                    userWins++;
                } else if (compPick > userPick) {
                    compWins++;
                }
                if (compPick == 1 && userPick == 3) {
                    compWins++;
                } else if (userPick > compPick) {
                    userWins++;
                } else if (compPick == userPick) {
                    totalTies++;
                }

                if (userWins > compWins) {
                    System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);

                } else if (userWins < compWins) {
                    System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);

                } else if (totalTies >= 1) {
                    System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);

                } else if (userWins == compWins) {
                    System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);

                }

            }

            if (userWins > compWins) {
                System.out.println("The final scores are: ");
                System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);
                System.out.println("You have won!");
            } else if (userWins < compWins) {
                System.out.println("The final scores are: ");
                System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);
                System.out.println("I have won!");
            } else if (userWins == compWins) {
                System.out.println("The final scores are: ");
                System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);
                System.out.println("We have tied.");
            } else {
                System.out.println("The final scores are: ");
                System.out.println("Computer Wins: " + compWins + " Your Wins: " + userWins + " Ties: " + totalTies);
                System.out.println("We have tied.");
            }

            System.out.println("Would you like to play again? 1 for yes or 2 for no. ");
            int userAnswer = sc.nextInt();

            switch (userAnswer) {
                case 1:
                    userAnswer = 1;
                    continue;
                case 2:
                    userAnswer = 2;
                    System.out.println("Thanks for playing!");
                    System.exit(0);
            }

        }
    }
}
