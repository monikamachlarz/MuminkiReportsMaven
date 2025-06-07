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
import java.util.ArrayList;

public class ExcelReader {

    private DataModel dataModel = new DataModel();

    public DataModel getDataModel() {
        return dataModel;
    }

    public void readExcel(String filePath) {

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new HSSFWorkbook(fis)) { // używamy HSSFWorkbook dla .xls

            String employeeLasNameSurname = filePath
                    .substring(filePath.lastIndexOf("\\") + 1, filePath.length() - 4)
                    .replace("_", " ");
//            System.out.println("nazwisko i imie: " + employeeLasNameSurname);
            Employee employee = dataModel.getEmployee(employeeLasNameSurname);
            employee.setSurnameName(employeeLasNameSurname);

            // Wyświetlenie nazw arkuszy (zakładek)
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Zakładki w pliku: ");
            for (int i = 0; i < numberOfSheets; i++) {
                System.out.println(" - " + workbook.getSheetName(i));
            }

            // Wczytywanie danych z pierwszego arkusza (możesz zmienić indeks, aby odczytać inne)
//            Sheet sheet = workbook.getSheetAt(0); // pierwszy arkusz

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                System.out.println("Odczyt z arkusza: " + sheet.getSheetName());

                String projectName = sheet.getSheetName();
                Project project = dataModel.getProject(projectName);

                // Odczyt danych z arkusza
                for (Row row : sheet) {

                    // pomija pierszy wiersz
                    if (row.getRowNum() == 0) {
                        continue;
                    }

                    Task task = new Task();
                    task.setEmployee(employee);

                    for (Cell cell : row) {
                        switch (cell.getCellType()) {
                            case STRING:
                                System.out.print("String" + cell.getStringCellValue() + "\t");

                                if (cell.getColumnIndex() == 0) {
                                    task.setDate(cell.getStringCellValue());
                                } else if (cell.getColumnIndex() == 1) {
                                    task.setTaskName(cell.getStringCellValue());
                                } else if (cell.getColumnIndex() == 2) {
                                    task.setHours(cell.getStringCellValue());
                                }

                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    System.out.print(cell.getDateCellValue() + "\t");
                                    if (cell.getColumnIndex() == 0) {
                                        task.setDate(String.valueOf(cell.getDateCellValue()));
                                    }
                                } else {
                                    System.out.print(cell.getNumericCellValue() + "\t");

                                    if (cell.getColumnIndex() == 2) {
                                        task.setHours(cell.getNumericCellValue());
                                    }
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

                    if (task.getDate() != null && task.getTaskName() != null && task.getHours() > 0) {
                        project.addTaskToList(task);
                    }

                    System.out.println(); // nowa linia po każdym wierszu

                }
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


        System.out.println();
        System.out.println();
        System.out.println();

        // test -> poprawny zapis danych w dataModel
        ArrayList<Project> projects = reader.dataModel.getProjectList();

        for (Project project : projects) {
            System.out.println("Project name :" + project.getName());

            for (Task task : project.getTaskList()) {
                System.out.println("Task: "
                        + task.getEmployee().getSurnameName() + "\t"
                        + task.getDate() + "\t"
                        + task.getTaskName() + "\t"
                        + task.getHours() + "\t"

                );

            }
        }
    }
}