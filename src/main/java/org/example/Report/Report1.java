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
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                if (task.getDate().getYear()==year) {
                    totalHours = totalHours + task.getHours();
                }
            }
            if (totalHours != 0) {
                System.out.println( employee.getName() + " " + totalHours);
            }

        }
    }
}

