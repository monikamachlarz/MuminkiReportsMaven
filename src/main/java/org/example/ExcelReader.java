package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader{

    public void readExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new HSSFWorkbook(fis)) { // używamy HSSFWorkbook dla .xls

            // Wyświetlenie nazw arkuszy (zakładek)
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Zakładki w pliku: ");
            for (int i = 0; i < numberOfSheets; i++) {
                System.out.println(" - " + workbook.getSheetName(i));
            }

            // Wczytywanie danych z pierwszego arkusza (możesz zmienić indeks, aby odczytać inne)
            Sheet sheet = workbook.getSheetAt(0); // pierwszy arkusz
            System.out.println("Odczyt z arkusza: " + sheet.getSheetName());

            // Odczyt danych z arkusza
            for (Row row : sheet) {
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                System.out.print(cell.getDateCellValue() + "\t");
                            } else {
                                System.out.print(cell.getNumericCellValue() + "\t");
                            }
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("BLANK\t");
                    }
                }
                System.out.println(); // nowa linia po każdym wierszu
            }

        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku Excel: " + e.getMessage());
        }
    }


    public void readAllExcelFilesRecursively(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.err.println("Nieprawidłowa ścieżka katalogu: " + directoryPath);
            return;
        }

        traverseAndRead(dir);
    }

    private void traverseAndRead(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                traverseAndRead(file); // 🔁 rekurencja dla podkatalogów
            } else if (file.getName().toLowerCase().endsWith(".xls")) {
                System.out.println("=== Odczyt pliku: " + file.getAbsolutePath() + " ===");
                readExcel(file.getAbsolutePath());
                System.out.println();
            }
        }
    }



    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader();
        //reader.readExcel("src/main/resources/2012/01/Kowalski_Jan.xls");
        reader.readAllExcelFilesRecursively("src/main/resources/2012"); // <- Podaj główny katalog z plikami .xls


    }
}