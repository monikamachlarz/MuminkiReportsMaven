

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;
import org.example.Report.Report2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Report2Test {

    private DataModel dataModel;
    private Report2 report;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setup() {
        dataModel = new DataModel("TestModel");

        // Przechwytywanie System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void shouldGenerateReportWithCorrectHoursForGivenYear() {
        Project project = dataModel.getProject("TestProject");
        Employee emp = dataModel.addEmployee("Jan Kowalski");

        new Task("Zadanie 1", project, 4.0, LocalDate.of(2024, 5, 1), emp);
        new Task("Zadanie 2", project, 2.0, LocalDate.of(2024, 6, 1), emp);
        new Task("Zadanie 3", project, 5.0, LocalDate.of(2023, 6, 1), emp); // inny rok

        report = new Report2(2024, dataModel);
        report.generateReport();

        String output = outputStream.toString();
        assertTrue(output.contains("TestProject"));
        assertTrue(output.contains("6.00")); // 4 + 2 z roku 2024
        assertFalse(output.contains("5.00")); // nie powinno być z 2023
    }

    @Test
    void shouldNotGenerateReportWhenNoTasksForGivenYear() {
        Project project = dataModel.getProject("EmptyYear");
        Employee emp = dataModel.addEmployee("Anna Nowak");

        new Task("OldTask", project, 10.0, LocalDate.of(2021, 1, 1), emp);

        report = new Report2(2022, dataModel);
        report.generateReport();

        String output = outputStream.toString();
        assertFalse(output.contains("EmptyYear"));
    }

    @Test
    void shouldHandleMultipleProjects() {
        Employee emp = dataModel.addEmployee("Marek Nowak");

        Project project1 = dataModel.getProject("ProjectA");
        Project project2 = dataModel.getProject("ProjectB");

        new Task("T1", project1, 3, LocalDate.of(2025, 1, 1), emp);
        new Task("T2", project2, 7, LocalDate.of(2025, 2, 1), emp);

        report = new Report2(2025, dataModel);
        report.generateReport();

        String output = outputStream.toString();
        assertTrue(output.contains("ProjectA"));
        assertTrue(output.contains("ProjectB"));
        assertFalse(output.contains("10.00")); // razem 10h
    }
}
