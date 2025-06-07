package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String choice = "";

        while (!choice.equals("exit")) {
            System.out.println("\n########## WORK REPORTS v0.1 ##########");
            System.out.println("Wybierz typ raportu: 1, 2, 3, 4, 5, 6");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    System.out.println("Podaj rok:");
                    String year = scanner.nextLine();
                    /* metoda wolajaca raport1(year) */
                }
                case "2" -> {
                    System.out.println("Podaj rok:");
                    String year = scanner.nextLine();
                    /* metoda wolajaca raport2(year) */
                }
                case "3" -> {
                    System.out.println("Podaj rok:");
                    String year = scanner.nextLine();
                    System.out.println("Podaj pracownika: ");
                    String employee = scanner.nextLine();
                    /* metoda wolajaca raport3(year, employee) */
                }
                case "4" -> {
                    System.out.println("Podaj rok:");
                    String year = scanner.nextLine();
                    System.out.println("Podaj pracownika: ");
                    String employee = scanner.nextLine();
                    /* metoda wolajaca raport4(year, employee) */
                }
                case "5" -> {
                    System.out.println("Podaj rok: ");
                    String year = scanner.nextLine();
                    System.out.println("Podaj pracownika: ");
                    String employee = scanner.nextLine();
                    /* metoda wolajaca raport5(year, employee) */
                }
                case "6" -> {
                    System.out.println("Podaj rok: ");
                    String year = scanner.nextLine();
                    System.out.println("Podaj tag: ");
                    String tag = scanner.nextLine();
                    /* metoda wolajaca raport6(year, tag) */
                }
            }
        }
    }
}