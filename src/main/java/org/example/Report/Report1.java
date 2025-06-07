package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

public abstract class Report1 implements Report{
    private String employeeName;
    private int year;

    public void EmployeeYearlyReport(String employeeName, int year) {
        this.employeeName = employeeName;
        this.year = year;
    }

    @Override
    public String generate(DataModel dataModel) {
        Employee emp = dataModel.getEmployee(employeeName);
        double totalHours = emp.getTaskList().stream()
                .filter(t -> t.getDate().getYear() == year)
                .mapToDouble(Task::getHours)
                .sum();

        return "Pracownik: " + employeeName + ", Rok: " + year + ", Łącznie godzin: " + totalHours;
    }
}
