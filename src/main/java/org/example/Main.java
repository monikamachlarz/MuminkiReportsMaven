package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        String choice = "";

        while (!choice.equals("koniec")) {
            System.out.println("\n########## WORK REPORTS ##########");
            System.out.println("Wybierz typ raportu: 1, 2, 3, 4, 5, 6. \nWpisz 'koniec' aby zamknac program");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    String year = readYear(scanner);
                    /* wygenerujRaport1(year); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport1(year)");
                }
                case "2" -> {
                    String year = readYear(scanner);
                    /* wygenerujRaport2(year); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport2(year)");
                }
                case "3" -> {
                    String year = readYear(scanner);
                    String employee = readEmployee(scanner);
                    /* wygenerujRaport3(year, employee\); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport3(year, employee)");
                }
                case "4" -> {
                    String year = readYear(scanner);
                    String employee = readEmployee(scanner);
                    /* wygenerujRaport4(year, employee); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport4(year, employee)");
                }
                case "5" -> {
                    String year = readYear(scanner);
                    String employee = readEmployee(scanner);
                    /* wygenerujRaport5(year, employee); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport5(year, employee)");
                }
                case "6" -> {
                    String year = readYear(scanner);
                    System.out.println("Podaj tag:");
                    String tag = scanner.nextLine();
                    /* wygenerujRaport6(year, tag); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport6(year, tag)");
                }
            }
        }
    }

    private static String readYear(Scanner scanner) {
        while (true) {
            System.out.println("Podaj rok (akceptowany format YYYY):");
            String year = scanner.nextLine();
            if (year.matches("\\d{4}")) {
                return year;
            } else {
                System.out.println("Nieprawidłowy format roku. Spróbuj ponownie (np. 2024).");
            }
        }
    }

    private static String readEmployee(Scanner scanner) {
        while (true) {
            System.out.println("Podaj pracownika (akceptowany format nazwisko_imie):");
            String employee = scanner.nextLine();
            if (employee.matches("\\p{L}+_\\p{L}+")) {
                return employee;
            } else {
                System.out.println("Nieprawidłowy format. Wprowadź nazwisko i imię w formacie nazwisko_imie (np. Kowalski_Jan).");
            }
        }
    }

}