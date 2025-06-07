package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;

public class ExcelReader{

//    public void readExcel(String filePath) {
//        try (FileInputStream fis = new FileInputStream(new File(filePath));
//             Workbook workbook = new HSSFWorkbook(fis)) { // używamy HSSFWorkbook dla .xls
//
//            // Wyświetlenie nazw arkuszy (zakładek)
//            int numberOfSheets = workbook.getNumberOfSheets();
//            System.out.println("Zakładki w pliku: ");
//            for (int i = 0; i < numberOfSheets; i++) {
//                System.out.println(" - " + workbook.getSheetName(i));
//            }
//
//            // Wczytywanie danych z pierwszego arkusza (możesz zmienić indeks, aby odczytać inne)
//            Sheet sheet = workbook.getSheetAt(0); // pierwszy arkusz
//            System.out.println("Odczyt z arkusza: " + sheet.getSheetName());
//
//            // Odczyt danych z arkusza
//            for (Row row : sheet) {
//                for (Cell cell : row) {
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                        case NUMERIC:
//                            if (DateUtil.isCellDateFormatted(cell)) {
//                                System.out.print(cell.getDateCellValue() + "\t");
//                            } else {
//                                System.out.print(cell.getNumericCellValue() + "\t");
//                            }
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
//                            break;
//                        case FORMULA:
//                            System.out.print(cell.getCellFormula() + "\t");
//                            break;
//                        default:
//                            System.out.print("BLANK\t");
//                    }
//                }
//                System.out.println(); // nowa linia po każdym wierszu
//            }
//
//        } catch (IOException e) {
//            System.err.println("Błąd podczas odczytu pliku Excel: " + e.getMessage());
//        }
//    }
//
//
//    public void readAllExcelFilesRecursively(String directoryPath) {
//        File dir = new File(directoryPath);
//        if (!dir.exists() || !dir.isDirectory()) {
//            System.err.println("Nieprawidłowa ścieżka katalogu: " + directoryPath);
//            return;
//        }
//
//        traverseAndRead(dir);
//    }
//
//    private void traverseAndRead(File dir) {
//        File[] files = dir.listFiles();
//        if (files == null) return;
//
//        for (File file : files) {
//            if (file.isDirectory()) {
//                traverseAndRead(file); // 🔁 rekurencja dla podkatalogów
//            } else if (file.getName().toLowerCase().endsWith(".xls")) {
//                System.out.println("=== Odczyt pliku: " + file.getAbsolutePath() + " ===");
//                readExcel(file.getAbsolutePath());
//                System.out.println();
//            }
//        }
//    }
public void readExcelIntoModel(String filePath, DataModel model) {
    String employeeName = new File(filePath).getName().replace(".xls", ""); // nazwisko z pliku

    try (FileInputStream fis = new FileInputStream(new File(filePath));
         Workbook workbook = new HSSFWorkbook(fis)) {

        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // pomiń nagłówek

            Cell dateCell = row.getCell(0);
            Cell projectCell = row.getCell(1);
            Cell hoursCell = row.getCell(2);

            if (dateCell == null || projectCell == null || hoursCell == null) continue;

            LocalDate date = dateCell.getLocalDateTimeCellValue().toLocalDate();
            String projectName = projectCell.getStringCellValue();
            double hours = hoursCell.getNumericCellValue();

            // Pobierz lub utwórz Project i Employee
            Project project = model.getProject(projectName);
            Employee employee = model.getEmployee(employeeName);

            // Utwórz Task
            Task task = new Task(projectName, project, hours, date, employee);

            // Dodaj zadanie do pracownika i projektu
            employee.addTask(task);
            project.addTask(task);
        }

    } catch (IOException e) {
        System.err.println("Błąd odczytu pliku: " + filePath);
    }
}
    public void readAllExcelFilesRecursively(String directoryPath, DataModel model) {
        File dir = new File(directoryPath);
        if (!dir.exists() || !dir.isDirectory()) return;
        traverseAndRead(dir, model);
    }

    private void traverseAndRead(File dir, DataModel model) {
        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                traverseAndRead(file, model);
            } else if (file.getName().toLowerCase().endsWith(".xls")) {
                readExcelIntoModel(file.getAbsolutePath(), model);
            }
        }
    }



    public static void main(String[] args) {
        ExcelReader reader = new ExcelReader();
        //reader.readExcel("src/main/resources/2012/01/Kowalski_Jan.xls");
       Project project = new Project("Project1");
        reader.readAllExcelFilesRecursively("src/main/resources/2012", new DataModel("model", project )); // <- Podaj główny katalog z plikami .xls


    }
}