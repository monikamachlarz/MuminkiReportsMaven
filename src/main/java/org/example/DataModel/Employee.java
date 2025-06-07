package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String surnameName;
    private List<Task> taskList = new ArrayList<>();

    public Employee(String surnameName) {
        this.surnameName = surnameName;
    }

    public String getSurnameName() {
        return surnameName;
    }


    public List<Task> getTaskList() {
        return taskList;
    }


}
