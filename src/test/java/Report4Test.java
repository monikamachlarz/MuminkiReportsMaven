import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;
import org.example.Report.Report1;
import org.example.Report.Report4;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Report4Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGenerateReport_withValidData() {

        Employee emp1 = new Employee("Kowalski Jan");
        Employee emp2 = new Employee("Kowalski Janek");

        Project project1 = new Project("Project1");
        Project project2 = new Project("Project2");

        new Task("Task 1", project1, 18, LocalDate.of(2025, 1, 1), emp1);
        new Task("Task 2", project1, 25.25, LocalDate.of(2025, 2, 1), emp1);
        new Task("Task 3", project2, 3.0, LocalDate.of(2024, 6, 1), emp2);

        DataModel dataModel = new DataModel(Arrays.asList(emp1, emp2).toString());
        Report4 report4 = new Report4(2025, dataModel, "Kowalski Jan");

        // Wygenerowanie raportu
        report4.generateReport();
        // Sprawdzenie wyniku
        String output = outContent.toString();
        assertTrue(output.contains("RAPORT: Report4"));
        assertTrue(output.contains("% czasu "));
        assertTrue(output.contains("-"));
        assertTrue(output.contains(" "));



    }
}
