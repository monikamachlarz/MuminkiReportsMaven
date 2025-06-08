package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private List<Task> taskList = new ArrayList<>();

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
    }


    public List<Task> getTaskList() {
        return taskList;
    }


}
