package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String SurnameName;
    private List<Task> taskList = new ArrayList<>();

    public Employee(String SurnameName) {
        this.SurnameName = SurnameName;
    }

    public String getSurnameName() {
        return SurnameName;
    }


    public List<Task> getTaskList() {
        return taskList;
    }


}
