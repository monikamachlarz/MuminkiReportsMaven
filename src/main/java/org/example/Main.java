package org.example;


import org.example.DataModel.DataModel;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        DataModel dataModel = new DataModel("MyDataModel");
        ExcelReader reader = new ExcelReader(dataModel);

        reader.readAllExcelFilesRecursively("src/main/resources/2012");

        Report1 reportGenerator = new Report1(dataModel);
        reportGenerator.print();

        //   ReportGenerator reportGenerator = new ReportGenerator(dataModel);
//        String choice = "";
//
//        while (!choice.equals("koniec")) {
//            System.out.println("\n########## WORK REPORTS v0.1 ##########");
//            System.out.println("Wybierz typ raportu: 1, 2, 3, 4, 5, 6. \nWpisz 'koniec' aby zamknac program");
//            Scanner scanner = new Scanner(System.in);
//            choice = scanner.nextLine();
//            switch (choice) {
//                case "1" -> {
//                    String rok = wczytajRok(scanner);
//                    /* wygenerujRaport1(rok); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport1(rok)");
//                }
//                case "2" -> {
//                    String rok = wczytajRok(scanner);
//                    /* wygenerujRaport2(rok); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport2(rok)");
//                }
//                case "3" -> {
//                    String rok = wczytajRok(scanner);
//                    String pracownik = wczytajPracownika(scanner);
//                    /* wygenerujRaport3(rok, pracownik); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport3(rok, pracownik)");
//                }
//                case "4" -> {
//                    String rok = wczytajRok(scanner);
//                    String pracownik = wczytajPracownika(scanner);
//                    /* wygenerujRaport4(rok, pracownik); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport4(rok, pracownik)");
//                }
//                case "5" -> {
//                    String rok = wczytajRok(scanner);
//                    String pracownik = wczytajPracownika(scanner);
//                    /* wygenerujRaport5(rok, pracownik); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport5(rok, pracownik)");
//                }
//                case "6" -> {
//                    String rok = wczytajRok(scanner);
//                    System.out.println("Podaj tag:");
//                    String tag = scanner.nextLine();
//                    /* wygenerujRaport6(rok, tag); */
//                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport6(rok, tag)");
//                }
//            }
//        }
//    }
//
//    private static String readYear(Scanner scanner) {
//        while (true) {
//            System.out.println("Podaj rok (akceptowany format YYYY):");
//            String year = scanner.nextLine();
//            if (year.matches("\\d{4}")) {
//                return year;
//            } else {
//                System.out.println("Nieprawidłowy format roku. Spróbuj ponownie (np. 2024).");
//            }
//        }
//    }
//
//    private static String readEmployee(Scanner scanner) {
//        while (true) {
//            System.out.println("Podaj pracownika (akceptowany format nazwisko_imie):");
//            String employee = scanner.nextLine();
//            if (employee.matches("\\p{L}+_\\p{L}+")) {
//                return employee;
//            } else {
//                System.out.println("Nieprawidłowy format. Wprowadź nazwisko i imię w formacie nazwisko_imie (np. Kowalski_Jan).");
//            }
//        }
//    }

    }
}