package org.example.DataModel;

import java.util.ArrayList;

public class DataModel {
    private String projectName;
    private ArrayList<Project> projectList = new ArrayList<>();

    private ArrayList<Employee> employeesList = new ArrayList<>();

    public DataModel(){
    }

//    public DataModel(String projectName) {
//        this.projectName = projectName;
//    }

    public ArrayList<Project> getTaskList() {
        return projectList;
    }

    public Project getProject(String projectName) {
        for (Project project : projectList) {
            if (project.getName().equals(projectName)) {
                return project;
            }
        }
        Project project = new Project(projectName);
        projectList.add(project);
        return project;
    }

    public Employee getEmployee(String surnameName) {
        for (Employee employee : employeesList) {
            if (employee.getSurnameName().equals(surnameName)) {
                return employee;
            }
        }
        Employee employee = new Employee(surnameName);
        employeesList.add(employee);
        return employee;
    }

    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }

    public ArrayList<Project> getProjectList() {
        return projectList;
    }

}
