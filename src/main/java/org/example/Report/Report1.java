package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

public class Report1  implements IReport {

    private DataModel dataModel;

    public Report1(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    @Override
    public void generateReport() {
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                totalHours += task.getHours();
            }
            System.out.println( employee.getName() + " " + totalHours);
        }

    }
}

