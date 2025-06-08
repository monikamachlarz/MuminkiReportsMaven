package org.example;


import org.example.DataModel.DataModel;
import org.example.Report.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        DataModel dataModel = new DataModel("MyDataModel");
        ExcelReader reader = new ExcelReader(dataModel);
        String startPath = "data";
        if (args.length>0)
            startPath = args[0];
        reader.readAllExcelFilesRecursively(startPath);


        String choice = "";

        while (!choice.equals("koniec")) {
            System.out.println("\n########## WORK REPORTS v0.1 ##########");
            System.out.println("Wybierz typ raportu: 1, 2, 3, 4, 5, 6. \nWpisz 'koniec' aby zamknac program");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            switch (choice) {
                case "1" -> {
                    String rok = readYear(scanner);
                    IReport report1 = new Report1(Integer.parseInt(rok), dataModel);
                    report1.generateReport();
                    List<String> reportToExport = report1.generateReportForExport();
                    report1.exportReportToPdf(reportToExport, "report1.pdf");
                }
                case "2" -> {
                    String rok = readYear(scanner);
                    IReport report2 = new Report2(Integer.parseInt(rok), dataModel);
                    report2.generateReport();
                }
                case "3" -> {
                    String rok = readYear(scanner);

                    System.out.println("Podaj pracownika (akceptowany format: nazwisko imie):");
                    String pracownik = scanner.nextLine();

                    IReport report3 = new Report3(Integer.parseInt(rok), dataModel, pracownik);
                    report3.generateReport();
                }
                case "4" -> {
                    String rok = readYear(scanner);
                    System.out.println("Podaj pracownika (akceptowany format: nazwisko imie):");
                    String pracownik = scanner.nextLine();

                    IReport report4 = new Report4(Integer.parseInt(rok), dataModel,pracownik);
                    report4.generateReport();


                }
                case "5" -> {
                    String rok = readYear(scanner);
                    System.out.println("Podaj nazwe projektu");
                    String nazwaProjektu = scanner.nextLine();
                    IReport report5 = new Report5(dataModel, Integer.parseInt(rok),  nazwaProjektu);
                    report5.generateReport();

                }
                case "6" -> {
                    String rok = readYear(scanner);
                    System.out.println("Podaj tag:");
                    String tag = scanner.nextLine();
                    /* wygenerujRaport6(rok, tag); */
                    System.out.println("Tutaj bedzie wolana metoda wygenerujRaport6(rok, tag)");
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

    }
