package org.example.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    private String surnameName;

    public Employee() {
    }

    public Employee(String surnameName) {
        this.surnameName = surnameName;
    }

    public void setSurnameName(String surnameName) {
        this.surnameName = surnameName;
    }

    public String getSurnameName() {
        return surnameName;
    }


}
