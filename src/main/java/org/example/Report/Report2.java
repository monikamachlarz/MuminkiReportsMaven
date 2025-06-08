package org.example.Report;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;

import java.time.LocalDate;

public class Report2 implements IReport{

    private DataModel dataModel;
    private int year;


    public Report2 (int year, DataModel dataModel){
        if ( year<1990 || year> 2100){
            return;
        }
        this.dataModel = dataModel;
        this.year = year;

    }

    public DataModel getDataModel() {
        return dataModel;
    }


    @Override
    public void generateReport() {
        System.out.println ("Report2: ");
        System.out.println("-------------------------------------------------------------");
        System.out.println ("|LP| Project name | Hours|");
        System.out.println("-------------------------------------------------------------");
        System.out.println();
        int number = 0;
        for (Project project : getDataModel().getProjectList()){
            double hours = 0;
            for (Task task : project.getTaskList()){
                if (task.getDate().getYear()==year) {
                    hours = hours + task.getHours();
                }
            }
            if (hours > 0){
                System.out.println ("| "+ ++number+".| " + project.getName()+" |" + hours+"|");
            }

        }
        System.out.println("-------------------------------------------------------------");

    }
}
