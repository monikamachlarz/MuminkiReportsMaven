package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Report3 implements IReport{
    private DataModel dataModel;
    private int year;
    private String name;


    public Report3 (int year, DataModel dataModel, String name) {
        if ( year<1990 || year> 2100){
            return;
        }
        this.dataModel = dataModel;
        this.year = year;
        this.name=name;

    }

    public String getName() {
        return name;
    }



    public Employee findEmployeeByName(String name) {


        Employee neededEmployee = new Employee("");
        for (Employee employee : dataModel.getEmployeesList()) {
            if (employee.getName().equals(name)) {
                neededEmployee = employee;

            }
            return neededEmployee;
        }

        return neededEmployee;
    }

    public DataModel getDataModel() {
        return dataModel;
    }


    @Override
    public void generateReport() {
        Employee employee = findEmployeeByName(getName());
        String className = this.getClass().getSimpleName();
        System.out.println("\n+=================== RAPORT: " + className + " ===================+");
        System.out.printf("| %-4s | %-4s | %-10s | %-15s | %-10s%n", "ID", "Rok", "Miesiac", "Nazwa projektu", " Godziny |");
        System.out.println("+------+------+------------+-----------------+----------+");

        int number = 1;  // Start from 1 or any other number you want

        HashMap<Month, ArrayList<Task>> monthTaskMap = new HashMap<>();

        // Group tasks by month
        for (Task task : employee.getTaskList()) {
            Month taskMonth = task.getDate().getMonth();

            if (!monthTaskMap.containsKey(taskMonth)) {
                monthTaskMap.put(taskMonth, new ArrayList<>());
            }
            monthTaskMap.get(taskMonth).add(task);
        }

        // For each month, calculate hours for each project
        for (Month localDate : monthTaskMap.keySet()) {
            HashMap<Project, Double> projectHours = new HashMap<>();

            // Accumulate hours for each project in the month
            for (Task task : monthTaskMap.get(localDate)) {
                Project project = task.getProject();
                double hours = task.getHours();

                // Add or accumulate hours for each project
                projectHours.put(project, projectHours.getOrDefault(project, 0.0) + hours);
            }

            // Print report for each project in the month
            for (Project project : projectHours.keySet()) {
                double totalHours = projectHours.get(project);

                // Print the report with an incrementing number
                printReport(number++, year, localDate, project.getName(), totalHours);
            }
        }

        System.out.println("+------+------+------------+-----------------+----------+");
    }

    // Print each report line
    public void printReport(int number, int year, Month localDate, String projectName, double hours) {
        System.out.printf("| %-4d | %-4d | %-10s | %-15s | %-8.2f |%n", number, year, localDate, projectName, hours);
    }




    @Override
    public void exportReportToPdf(List<String> lines, String outputPath) {

    }

    @Override
    public List<String> generateReportForExport() {
        return List.of();
    }

}