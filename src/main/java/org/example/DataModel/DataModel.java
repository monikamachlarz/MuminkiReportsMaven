package org.example.DataModel;

import java.util.ArrayList;

public class DataModel {
    private String Name;
    private ArrayList<Project> ProjectList = new ArrayList<>();
    public DataModel(String Name, Project project) {
        this.Name = Name;

    }
    public ArrayList<Project> getTaskList() {
        return ProjectList;
    }

}
