package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.util.HashMap;

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

        System.out.println("Report4: ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("|LP| Project name |   %|");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        int number = 0;
        for (String projectName : projectsHour.keySet()) {
            double averageHours = projectsHour.get(projectName) / totalHours;
            double roundAverageHours = Math.round(averageHours * 100.0);
            System.out.println("| " + ++number + ".| " + projectName + " |" + Math.floor(roundAverageHours) + "|");
        }

        System.out.println("-------------------------------------------------------------");

    }
}
