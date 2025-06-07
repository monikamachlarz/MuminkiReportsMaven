package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;

    private List<Task> taskList = new ArrayList<>();

    public Project(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

}
