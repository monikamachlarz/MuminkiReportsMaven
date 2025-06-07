package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String Name;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Project(String name, List<Task> taskList) {
        this.Name = name;
    }
    public String getName() {
        return Name;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
