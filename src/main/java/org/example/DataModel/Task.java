package org.example.DataModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Task {

    private String taskName;
    private Project project;
    private double hours;
    private LocalDate date;
    private Employee employee;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Task(){
    }

//    public Task(String Name, Project project, double hours, LocalDate date, Employee employee) {
//        this.taskName = Name;
//        this.project = project;
//        this.hours = hours;
//        this.date = date;
//        this.employee = employee;
//    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public void setHours(String hours) {
        this.hours = Double.parseDouble(hours);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            Date date = sdf.parse(string);
            this.date = date.toInstant()
                    .atZone(ZoneId.of("Europe/Warsaw"))
                    .toLocalDate();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTaskName() {
        return taskName;
    }

    public Project getProject() {
        return project;
    }

    public double getHours() {
        return hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public Employee getEmployee() {
        return employee;
    }

}
