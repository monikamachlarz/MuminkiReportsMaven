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
             Workbook workbook = new HSSFWorkbook(fis)) {
            int numberOfSheets = workbook.getNumberOfSheets();
            System.out.println("Zakładki w pliku: ");
            for (int i = 0; i < numberOfSheets; i++) {

                Sheet sheet = workbook.getSheetAt(i);
                String projectName = sheet.getSheetName();
                System.out.println("Odczyt z arkusza: " + projectName);
                Project project = dataModel.getProject(projectName);

                boolean isHeader = true;
                int numberOfRows = 1;

                for (Row row : sheet) {
                    if (isHeader) {
                        isHeader = false;
                        numberOfRows++;
                        continue;
                    }

                    Cell dateCell = row.getCell(0);
                    Cell taskCell = row.getCell(1);
                    Cell hoursCell = row.getCell(2);

                    if (dateCell == null || dateCell.getCellType() != CellType.NUMERIC || !DateUtil.isCellDateFormatted(dateCell)) {
                        System.out.println("Error: Błąd w lini " + numberOfRows + " nieprawidlowy format daty lub jej brak");
                        numberOfRows++;
                        continue;
                    }
                    Date dateValue = dateCell.getDateCellValue();
                    LocalDate date = dateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    String taskName = (taskCell != null && taskCell.getCellType() == CellType.STRING) ? taskCell.getStringCellValue() : "";
                    if (taskName == null || taskName.isEmpty()) {
                        System.out.println("Error: Błąd w lini " + numberOfRows+" : Nieprawidlowy format lub brak taska");
                        numberOfRows++;
                        continue;
                    }

                    if (hoursCell == null ) {
                        System.out.println("Error: Błąd w lini " + numberOfRows+" : Brak godziny");
                        numberOfRows++;
                        continue;
                    }
                    double hours = 0;
                    if (hoursCell != null) {
                        if (hoursCell.getCellType() == CellType.NUMERIC) {
                            hours = hoursCell.getNumericCellValue();
                        } else if (hoursCell.getCellType() == CellType.STRING) {
                            try {
                                hours = Double.parseDouble(hoursCell.getStringCellValue());
                            } catch (NumberFormatException e) {
                                hours = 0;
                            }
                        }
                    }
                    Task task = new Task(taskName, project, hours, date, employee);
                    numberOfRows++;

                    //employee.getTaskList().add(task);
                  //  project.getTaskList().add(task);
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