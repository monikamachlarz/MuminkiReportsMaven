package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.util.HashMap;
import java.util.List;

public class Report4 implements IReport {

    private DataModel dataModel;
    private int year;
    private String name;

    public Report4(int year, DataModel dataModel, String name) {
        this.year = year;
        this.dataModel = dataModel;
        this.name = name;
    }

    public DataModel getDataModel() {
        return dataModel;
    }

    @Override
    public void generateReport() {

        HashMap<String, Double> projectsHour = new HashMap<>();

        for (Project project : getDataModel().getProjectList()) {

            String projectName = project.getName();

            double hours = 0;
            for (Task task : project.getTaskList()) {
                if (task.getDate().getYear() == year && task.getEmployee().getName().equals(name)) {
                    hours = hours + task.getHours();

                    if (projectsHour.containsKey(projectName)) {
                        hours = projectsHour.get(projectName) + task.getHours();
                        projectsHour.put(projectName, hours);
                    } else {
                        projectsHour.put(projectName, hours);
                    }
                }
            }
        }

        double totalHours = 0;
        for (String projectName : projectsHour.keySet()) {
            totalHours += projectsHour.get(projectName);
        }

        String className = this.getClass().getSimpleName();
        System.out.println("\n+================= RAPORT: " + className + " ==================+");
        System.out.printf("| %-4s | %-29s | %-10s%n", "ID", "Nazwa projektu", " % czasu    |");
        System.out.println("+------+-------------------------------+-------------+");

        int id = 1;
        for (String projectName : projectsHour.keySet()) {
            double averageHours = projectsHour.get(projectName) / totalHours;
            double roundAverageHours = Math.round(averageHours * 100.0);
            printReport(id++, projectName, Math.floor(roundAverageHours));
        }

        System.out.println("------------------------------------------------------");

    }

    public void printReport(int id, String projectName, double percentage) {
        System.out.printf("| %-4d | %-29s | %-11.2f |%n", id, projectName, percentage);
    }

    @Override
    public void exportReportToPdf(List<String> lines, String outputPath) {}
}
