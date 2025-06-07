package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;

    private ArrayList<Task> taskList = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTaskToList(Task task) {
        taskList.add(task);
    }

}
