package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.util.HashMap;
import java.util.Map;

public class Report5 implements IReport{
    private DataModel dataModel;
    private int year;
    private String projectName;

    public Report5(DataModel dataModel, int year, String projectName) {
        this.dataModel = dataModel;
        this.year = year;
        this.projectName = projectName;
    }

    @Override
    public void generateReport() {
        String className = this.getClass().getSimpleName();
        System.out.println("\n+=============================================== RAPORT: " + className + " ================================================+");
        System.out.printf("| %-4s | %-90s | %-10s%n", "ID", "Task Name", "Hours      |");
        System.out.println("+------+--------------------------------------------------------------------------------------------+------------+");

        int id = 1;
        HashMap<String, Double> taskHashMap = new HashMap<>();
        for (Project project : dataModel.getProjectList()) {
            if (project.getName().equals(projectName)) {
                for(Task task : project.getTaskList()) {
                    if (task.getDate().getYear() == year) {
                        taskHashMap.put(task.getName(), taskHashMap.getOrDefault(task.getName(), 0.0) + task.getHours());
                    }
                }
            }
        }
        for (Map.Entry<String, Double> entry : taskHashMap.entrySet()) {
            System.out.printf("| %-4d | %-90s | %-10.2f |%n", id++, entry.getKey(), entry.getValue());
        }

        System.out.println("+------+--------------------------------------------------------------------------------------------+------------+");
    }
}
