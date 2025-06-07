package org.example;


import org.example.DataModel.DataModel;
import org.example.Report.Report;
import org.example.Report.ReportExporter;

import java.io.IOException;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) throws IOException {
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
//    private static String wczytajRok(Scanner scanner) {
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
//    private static String wczytajPracownika(Scanner scanner) {
//        while (true) {
//            System.out.println("Podaj pracownika (akceptowany format nazwisko_imie):");
//            String input = scanner.nextLine();
//            if (input.matches("\\p{L}+_\\p{L}+")) {
//                return input;
//            } else {
//                System.out.println("Nieprawidłowy format. Wprowadź nazwisko i imię w formacie nazwisko_imie (np. Kowalski_Jan).");
//            }
//        }
//    }
private static final String DANE_FOLDER = "dane";
    private static final String OUTPUT_FOLDER = "raporty";

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String choice = "";

        while (!choice.equalsIgnoreCase("koniec")) {
            System.out.println("\n########## WORK REPORTS v0.1 ##########");
            System.out.println("Wybierz typ raportu: 1, 2, 3, 4, 5, 6. \nWpisz 'koniec' aby zamknąć program");
            choice = scanner.nextLine();

            DataModel model = new DataModel("Raport", null);
            ExcelReader reader = new ExcelReader();
            reader.readAllExcelFilesRecursively(DANE_FOLDER, model);

            switch (choice) {
                case "1" -> {
                    String rok = wczytajRok(scanner);
                    wygenerujRaport1(model, rok);
                }
                case "2" -> {
                    String rok = wczytajRok(scanner);
                    wygenerujRaport2(model, rok);
                }
                case "3" -> {
                    String rok = wczytajRok(scanner);
                    String pracownik = wczytajPracownika(scanner);
                    wygenerujRaport3(model, rok, pracownik);
                }
                case "4" -> {
                    String rok = wczytajRok(scanner);
                    String pracownik = wczytajPracownika(scanner);
                    wygenerujRaport4(model, rok, pracownik);
                }
                case "5" -> {
                    String rok = wczytajRok(scanner);
                    String pracownik = wczytajPracownika(scanner);
                    wygenerujRaport5(model, rok, pracownik);
                }
                case "6" -> {
                    String rok = wczytajRok(scanner);
                    System.out.println("Podaj tag:");
                    String tag = scanner.nextLine();
                    wygenerujRaport6(model, rok, tag);
                }
            }
        }
    }

    private static String wczytajRok(Scanner scanner) {
        while (true) {
            System.out.println("Podaj rok (np. 2024):");
            String year = scanner.nextLine();
            if (year.matches("\\d{4}")) return year;
            System.out.println("Nieprawidłowy format roku.");
        }
    }

    private static String wczytajPracownika(Scanner scanner) {
        while (true) {
            System.out.println("Podaj pracownika (nazwisko_imie):");
            String input = scanner.nextLine();
            if (input.matches("\\p{L}+_\\p{L}+")) return input.toLowerCase();
            System.out.println("Nieprawidłowy format.");
        }
    }

    // Przykład raportu 3 – suma godzin pracownika w roku
    private static void wygenerujRaport3(DataModel model, String rok, String pracownik) {
        int year = Integer.parseInt(rok);
        Report report = new EmployeeYearlyReport(pracownik, year);
        String wynik = report.generate(model);

        System.out.println("\n" + wynik);

        String filenameBase = OUTPUT_FOLDER + "/raport3_" + pracownik + "_" + rok;
        ReportExporter.exportToPdf(wynik, filenameBase + ".pdf");
        ReportExporter.exportToXlsx(wynik, filenameBase + ".xlsx");
    }

    // Dla pozostałych raportów użyjesz podobnych metod:
    private static void wygenerujRaport1(DataModel model, String rok) {
        // Np. suma godzin wszystkich pracowników w danym roku
    }

    private static void wygenerujRaport2(DataModel model, String rok) {
        // Np. zestawienie godzin na projekt
    }

    private static void wygenerujRaport4(DataModel model, String rok, String pracownik) {}
    private static void wygenerujRaport5(DataModel model, String rok, String pracownik) {}
    private static void wygenerujRaport6(DataModel model, String rok, String tag) {}
}