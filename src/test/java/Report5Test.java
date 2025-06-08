

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;
import org.example.Report.Report5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Report5Test {

    private DataModel dataModel;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        dataModel = new DataModel("TestModel");

        // Przechwycenie System.out
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void shouldGenerateReportWithCorrectTasksAndHoursForProjectAndYear() {
        Employee emp = dataModel.addEmployee("Jan Kowalski");
        Project project = dataModel.getProject("Alpha");

        new Task("Implementacja", project, 5.0, LocalDate.of(2024, 3, 10), emp);
        new Task("Testowanie", project, 3.0, LocalDate.of(2024, 3, 15), emp);
        new Task("Testowanie", project, 2.0, LocalDate.of(2024, 4, 1), emp);
        new Task("Implementacja", project, 1.5, LocalDate.of(2023, 12, 31), emp); // inny rok

        Report5 report = new Report5(dataModel, 2024, "Alpha");
        report.generateReport();

        String output = outputStream.toString();
        assertTrue(output.contains("Task: Implementacja, Total hours: 5.0"));
        assertTrue(output.contains("Task: Testowanie, Total hours: 5.0"));
        assertFalse(output.contains("1.5")); // nie powinien uwzględniać zadania z 2023
    }

    @Test
    void shouldIgnoreTasksFromDifferentProjects() {
        Employee emp = dataModel.addEmployee("Anna Nowak");

        Project project1 = dataModel.getProject("Alpha");
        Project project2 = dataModel.getProject("Beta");

        new Task("Zadanie A", project1, 4.0, LocalDate.of(2024, 1, 1), emp);
        new Task("Zadanie A", project2, 10.0, LocalDate.of(2024, 2, 1), emp); // inny projekt

        Report5 report = new Report5(dataModel, 2024, "Alpha");
        report.generateReport();

        String output = outputStream.toString();
        assertFalse(output.contains("Task: Zadanie 1, Total hours: 4.0"));
        assertFalse(output.contains("10.0"));
    }

    @Test
    void shouldGenerateEmptyReportIfNoMatchingTasks() {
        Project project = dataModel.getProject("Projekt2");
        dataModel.addEmployee("Zenek");

        Report5 report = new Report5(dataModel, 2025, "Projekt2");
        report.generateReport();

        String output = outputStream.toString();
        assertTrue(output.isBlank());
    }
}
