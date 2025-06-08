package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

public class Report1  implements IReport {

    private DataModel dataModel;
    private int year;

    public Report1(int year, DataModel dataModel) {
        this.year = year;
        this.dataModel = dataModel;
    }

    @Override
    public void generateReport() {
        String className = this.getClass().getSimpleName();
        System.out.println("\n+=============== RAPORT: " + className + " =============+");
        System.out.printf("%-31s | %-10s%n", "| Employee Name", "Total Hours |");
        System.out.println("+-------------------------------+-------------+");
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                if (task.getDate().getYear()==year) {
                    totalHours = totalHours + task.getHours();
                }
            }
            printReport(employee.getName(), totalHours);
        }
        System.out.println("+=============================================+");
    }

    public void printReport(String employee, double totalHours) {
        if (totalHours > 0) {
            System.out.printf("| %-29s | %-11.2f |%n", employee, totalHours);
        }
    }
}

