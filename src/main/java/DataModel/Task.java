package src.main.java.DataModel;

import java.time.LocalDate;

public class Task {
    private String Name;
    private Project project;
    private double hours;
    private LocalDate Date;
    private Employee employee;

    public Task(String Name, Project project, double hours, LocalDate Date, Employee employee) {
        this.Name = Name;
        this.project = project;
        this.hours = hours;
        this.Date = Date;
        this.employee = employee;
    }

    public String getName() {
        return Name;
    }

    public Project getProject() {
        return project;
    }

    public double getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return Date;
    }

    public Employee getEmployee() {
        return employee;
    }
}
