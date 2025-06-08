package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.time.LocalDate;

public class Report2 implements IReport{

    private DataModel dataModel;
    private int year;


    public Report2 (int year, DataModel dataModel){
        if ( year<1990 || year> 2100){
            return;
        }
        this.dataModel = dataModel;
        this.year = year;

    }

    public DataModel getDataModel() {
        return dataModel;
    }


    @Override
    public void generateReport() {
        String className = this.getClass().getSimpleName();
        System.out.println("\n+================= RAPORT: " + className + " ==================+");
        System.out.printf("| %-4s | %-29s | %-10s%n", "ID", "Employee Name", "Total Hours |");
        System.out.println("+------+-------------------------------+-------------+");

        int id = 1;
        for (Project project : getDataModel().getProjectList()){
            double hours = 0;
            for (Task task : project.getTaskList()){
                if (task.getDate().getYear()==year) {
                    hours = hours + task.getHours();
                }
            }
            if (hours > 0) {
                printReport(id++, project.getName(), hours);
            }
        }
        System.out.println("+====================================================+");
    }

    public void printReport(int id, String employee, double totalHours) {
        System.out.printf("| %-4d | %-29s | %-11.2f |%n", id, employee, totalHours);
    }

}
