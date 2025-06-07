package org.example.Test;

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;
import org.example.Report.Report1;
import org.example.Report.Report2;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Locale;

public class ReportTest {

    @Test
    public void Report1PrintTest(){
        Project Pentagon = new Project("PentagonTorrentTracker");
        Employee employee1 = new Employee("Rambo John");
        Employee employee2 = new Employee("McClane John");
        Employee employee3 = new Employee("Wick John");
        LocalDate date = LocalDate.of(2025, 5, 25);

        Task task1 = new Task("Fishing attack Pentagon film server", Pentagon, 2.5, date, employee1);
        Task task2 = new Task("Send spam", Pentagon, 1.5, date, employee1);

        Task task3 = new Task("Programming of trojans", Pentagon, 2.5, date, employee2);
        Task task4 = new Task("Switch off Windows Defender", Pentagon, 2.0, date, employee2);

        Task task5 = new Task("Install Kaspersky Antivirus", Pentagon, 2.0, date, employee3);
        Task task6 = new Task("Run Torrent Tracker", Pentagon, 3.5, date, employee3);
        int year = 2025;


        DataModel dataModel = new DataModel("First model", Pentagon);
        dataModel.getEmployeesList().add(employee1);
        dataModel.getEmployeesList().add(employee2);
        dataModel.getEmployeesList().add(employee3);
        Report1 report1 = new Report1("First report", year, dataModel);
        report1.GenerateReport();

    }

    @Test
    public void Report2PrintTest(){
        Project Pentagon = new Project("PentagonTorrentTracker");
        Employee employee1 = new Employee("Rambo John");
        Employee employee2 = new Employee("McClane John");
        Employee employee3 = new Employee("Wick John");
        LocalDate date = LocalDate.of(2025, 5, 25);

        Task task1 = new Task("Fishing attack Pentagon film server", Pentagon, 2.5, date, employee1);
        Task task2 = new Task("Send spam", Pentagon, 1.5, date, employee1);

        Task task3 = new Task("Programming of trojans", Pentagon, 2.5, date, employee2);
        Task task4 = new Task("Switch off Windows Defender", Pentagon, 2.0, date, employee2);

        Task task5 = new Task("Install Kaspersky Antivirus", Pentagon, 2.0, date, employee3);
        Task task6 = new Task("Run Torrent Tracker", Pentagon, 3.5, date, employee3);
        int year = 2025;


        DataModel dataModel = new DataModel("First model", Pentagon);
        dataModel.getEmployeesList().add(employee1);
        dataModel.getEmployeesList().add(employee2);
        dataModel.getEmployeesList().add(employee3);
        dataModel.getAllProjects().add(Pentagon);
        Report2 report2 = new Report2("Second report", year, dataModel);
        report2.GenerateReport();

    }

}
