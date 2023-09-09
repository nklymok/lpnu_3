package com.nklymok;

import com.nklymok.fraction.Fraction;
import com.nklymok.harmonic_series.BigIntegerHarmonicSeries;
import com.nklymok.harmonic_series.HarmonicSeries;
import com.nklymok.harmonic_series.IntHarmonicSeries;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AppMenu {
    private final Scanner sc = new Scanner(System.in);

    public void start() {
        welcomeScreen();
        appLoop();
    }

    private void appLoop() {
        while (true) {
            int n = inputSteps();
            HarmonicSeries series = getHarmonicSeries(n);
            confirmRun(series);
            if (!tryAgain()) {
                System.out.println("Bye!");
                return;
            }
        }
    }

    private boolean tryAgain() {
        while (true) {
            System.out.print("Do you want to continue? (y/n): ");
            String input = sc.next().trim().toLowerCase();

            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            }
            System.out.println("Invalid input. Please enter 'y' or 'n'.");
        }
    }

    private void confirmRun(HarmonicSeries series) {
        System.out.println("Harmonic series is ready to run.");
        System.out.println("Type: " + series.getClass().getSimpleName());
        System.out.println("Step count: " + series.getStepCount());
        System.out.println("To run, press enter.");
        sc.nextLine();
        Fraction result = series.calculate();
        System.out.println("Result: " + result.toString());
    }

    private HarmonicSeries getHarmonicSeries(int n) {
        if (n > 15) {
            return new BigIntegerHarmonicSeries(n);
        }
        return new IntHarmonicSeries(n);
    }

    private int inputSteps() {
        System.out.print("Enter step amount: ");
        try {
            int n = sc.nextInt();
            sc.nextLine();
            return validatedN(n);
        } catch (InputMismatchException e) {
            System.out.println("The number you entered is invalid. See the detailed error message.");
            throw e;
        }
    }

    private int validatedN(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("You cannot enter N that is less than 1. Please try again.");
        }
        return n;
    }

    private void welcomeScreen() {
        System.out.println("===\t\tMade by Nazarii Klymok, PZ-32\t===");
        System.out.println("===\t\tHarmonic Series calculator\t\t===");
    }

}
