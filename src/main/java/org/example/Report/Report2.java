package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.time.LocalDate;

public class Report2 implements Report{

    private String Name;
    private DataModel dataModel;
    private int year;


    public Report2 (String Name, int year, DataModel dataModel){
        if ( year<1990 || year> 2100){
            return;
        }
        this.Name = Name;
        this.dataModel = dataModel;
        this.year = year;

    }

    public DataModel getDataModel() {
        return dataModel;
    }

    public String getName (){
        return Name;
    }

    @Override
    public void GenerateReport() {

        System.out.println ("Report: "+getName());
        System.out.println("-------------------------------------------------------------");
        System.out.println ("|LP| Project name | Hours|");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        int number = 0;
        for (Project project : getDataModel().getAllProjects()){
            double hours = 0;
            for (Task task : project.getTaskList()){
                if (task.getDate().getYear()==year) {
                    hours = hours + task.getHours();
                }
            }
            System.out.println ("| "+ ++number+".| " + project.getName()+" |" + hours+"|");

        }
        System.out.println("-------------------------------------------------------------");

    }
}
