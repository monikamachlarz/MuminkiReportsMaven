package org.example;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

public class WorkHoursReportGenerator {

    private DataModel dataModel;

    public WorkHoursReportGenerator(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void printTotalHoursForEmployees() {
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                totalHours += task.getHours();
            }
            System.out.println( employee.getName() + " " + totalHours);

        }

    }
}

