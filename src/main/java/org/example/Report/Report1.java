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

    public void generateReport() {
        String className = this.getClass().getSimpleName();
        System.out.println("\n+================= RAPORT: " + className + " ==================+");
        System.out.printf("| %-4s | %-29s | %-10s%n", "ID", "Employee Name", "Total Hours |");
        System.out.println("+------+-------------------------------+-------------+");

        int id = 1;
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                if (task.getDate().getYear() == year) {
                    totalHours += task.getHours();
                }
            }
            if (totalHours > 0) {
                printReport(id++, employee.getName(), totalHours);
            }
        }
        System.out.println("+====================================================+");
    }

    public void printReport(int id, String employee, double totalHours) {
        System.out.printf("| %-4d | %-29s | %-11.2f |%n", id, employee, totalHours);
    }

}

