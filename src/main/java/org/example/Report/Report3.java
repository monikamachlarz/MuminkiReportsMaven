package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;

public class Report3 implements IReport{
    private DataModel dataModel;
    private int year;
    private String name;


    public Report3 (int year, DataModel dataModel, String name) {
        if ( year<1990 || year> 2100){
            return;
        }
        this.dataModel = dataModel;
        this.year = year;
        this.name=name;

    }

    public String getName() {
        return name;
    }



    public Employee findEmployeeByName(String name) {


        Employee neededEmployee = new Employee("");
        for (Employee employee : dataModel.getEmployeesList()) {
            if (employee.getName().equals(name)) {
                neededEmployee = employee;

            }
            return neededEmployee;
        }

        return neededEmployee;
    }

    public DataModel getDataModel() {
        return dataModel;
    }


    @Override
    public void generateReport() {
        Employee employee = findEmployeeByName(getName());
        //System.out.println(employee.getName());
        System.out.println ("Report3: ");
        System.out.println("-------------------------------------------------------------");
        System.out.println ("|LP| Year - Month | Project| Hours |");
        System.out.println("-------------------------------------------------------------");
        System.out.println();

        int number = 0;

        HashMap <Month, ArrayList<Task>> monthTaskMap = new HashMap <>();
        for (Task task : employee.getTaskList()) {
            Month taskMonth = task.getDate().getMonth();

            if (!monthTaskMap.containsKey(taskMonth)) {
                monthTaskMap.put(taskMonth, new ArrayList<>());
                monthTaskMap.get(taskMonth).add(task);
                //System.out.println ("Kolejny miesiąc");
            } else {
                monthTaskMap.get(taskMonth).add(task);
                //System.out.println ("Kolejne zadanie dodane");
            }
        }



        for (Month localDate : monthTaskMap.keySet()) {
            HashMap <Project, Double> projectHours = new HashMap <>();
            System.out.print (" "+year + " - " + localDate);

            for (int i = 0; i < monthTaskMap.get(localDate).size(); i++) {
                double hours = 0;
                if (!projectHours.containsKey(monthTaskMap.get(localDate).get(i).getProject())) {
                    hours += monthTaskMap.get(localDate).get(i).getHours();
                    projectHours.put(monthTaskMap.get(localDate).get(i).getProject(), hours);

                }else{

                    hours = projectHours.get(monthTaskMap.get(localDate).get(i).getProject())+monthTaskMap.get(localDate).get(i).getHours();
                    projectHours.put(monthTaskMap.get(localDate).get(i).getProject(), hours);
                }



            }

            for (Project project : projectHours.keySet()) {
                System.out.println(project.getName() + " " + projectHours.get(project));
            }




        }





        }


    }

