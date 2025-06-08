package org.example;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

public class Report1 {

    private DataModel dataModel;

    public Report1(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public void print() {
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                totalHours += task.getHours();
            }
            System.out.println( employee.getName() + " " + totalHours);

        }

    }
}

