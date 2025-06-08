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
import java.time.ZoneId;
import java.util.Date;

public class ExcelReader{

    private DataModel dataModel;


    public ExcelReader(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void readExcel(String filePath) {
        File file = new File(filePath);

        String fileName = file.getName().replace(".xls", "");
        String employeeName = fileName.replace('_', ' ');
        Employee employee = dataModel.addEmployee(employeeName);
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new HSSFWorkbook(fis)) { // używamy HSSFWorkbook dla .xls
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Zakładki w pliku: ");
            for (int i = 0; i < numberOfSheets; i++) {

                Sheet sheet = workbook.getSheetAt(i);
                String projectName = sheet.getSheetName();
                System.out.println("Odczyt z arkusza: " + projectName);
                Project project = dataModel.getProject(projectName);

                boolean isHeader = true;
                for (Row row : sheet) {
                    if (isHeader) {
                        isHeader = false;
                        continue;
                    }

                    Cell dateCell = row.getCell(0);
                    System.out.println(dateCell);
                    Cell taskCell = row.getCell(1);
                    System.out.println(taskCell);
                    Cell hoursCell = row.getCell(2);
                    System.out.println(hoursCell);

                    if (dateCell == null || dateCell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(dateCell)) {
                        continue;
                    }
                    Date dateValue = dateCell.getDateCellValue();
                    LocalDate date = dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String taskName = (taskCell != null && taskCell.getCellType() == CellType.STRING) ? taskCell.getStringCellValue() : "";

                    double hours = 0;
                    if (hoursCell != null) {
                        if (hoursCell.getCellType() == CellType.NUMERIC) {
                            hours = hoursCell.getNumericCellValue();
                        } else if (hoursCell.getCellType() == CellType.STRING) {
                            try {
                                hours = Double.parseDouble(hoursCell.getStringCellValue());
                            } catch (NumberFormatException e) {
                                hours = 0;
//             // Wyświetlenie nazw arkuszy (zakładek)
//             int numberOfSheets = workbook.getNumberOfSheets();
//             System.out.println("Zakładki w pliku: ");
//             for (int i = 0; i < numberOfSheets; i++) {
//                 System.out.println(" - " + workbook.getSheetName(i));
//             }

//             // Wczytywanie danych z pierwszego arkusza (możesz zmienić indeks, aby odczytać inne)
//             Sheet sheet = workbook.getSheetAt(0); // pierwszy arkusz
//             System.out.println("Odczyt z arkusza: " + sheet.getSheetName());

//             // Odczyt danych z arkusza
//             for (Row row : sheet) {
//                 for (Cell cell : row) {
//                     switch (cell.getCellType()) {
//                         case STRING:
//                             System.out.print(cell.getStringCellValue() + "\t");
//                             break;
//                         case NUMERIC:
//                             if (DateUtil.isCellDateFormatted(cell)) {
//                                 System.out.print(cell.getDateCellValue() + "\t");
//                             } else {
//                                 System.out.print(cell.getNumericCellValue() + "\t");
// >>>>>>> main
                            }
                        }
                    }

                    Task task = new Task(taskName, project, hours, date, employee);

                    /*employee.getTaskList().add(task);
                    project.getTaskList().add(task);*/
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
                traverseAndRead(file);
            } else if (file.getName().toLowerCase().endsWith(".xls")) {
                System.out.println("=== Odczyt pliku: " + file.getAbsolutePath() + " ===");
                readExcel(file.getAbsolutePath());
                System.out.println();
            }
        }
    }

}