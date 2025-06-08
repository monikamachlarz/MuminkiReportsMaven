

import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Project;
import org.example.DataModel.Task;
import org.example.Report.Report1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Report1Test {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testGenerateReport_withValidData() {

        Employee emp1 = new Employee("Jan Nowak");
        Employee emp2 = new Employee("Marcin Kowalski");

        Project project1 = new Project("Project A");
        Project project2 = new Project("Project B");

        new Task("Task A", project1, 5.5, LocalDate.of(2025, 3, 1), emp1);
        new Task("Task Old", project1, 10.0, LocalDate.of(2022, 5, 1), emp1);
        new Task("Task B", project2, 3.0, LocalDate.of(2023, 6, 1), emp2);

        DataModel dataModel = new DataModel(Arrays.asList(emp1, emp2).toString());
        Report1 report = new Report1(2023, dataModel);

        // Wygenerowanie raportu
        report.generateReport();

        // Sprawdzenie wyniku
        String output = outContent.toString();
        assertFalse(output.contains("Jan Nowak"));
        assertFalse(output.contains("Marcin Kowalski"));
        assertFalse(output.contains("5.50"));
        assertFalse(output.contains("3.00"));
    }
    @Test
    void testGenerateReport_withPartialYearTasks() {
        // Pracownik z zadaniami w dwóch różnych latach
        Employee emp = new Employee("Multi-Year Worker");
        Project project = new Project("Cross-Year Project");

        // Zadanie z 2025 (ma być uwzględnione)
        new Task("2025 Task", project, 4.0, LocalDate.of(2025, 2, 15), emp);

        // Zadanie z 2024 (nie ma być uwzględnione)
        new Task("2024 Task", project, 6.0, LocalDate.of(2024, 12, 30), emp);

        // Raport
        DataModel dataModel = new DataModel(Collections.singletonList(emp).toString());
        Report1 report = new Report1(2023, dataModel);

        // Przechwytywanie i sprawdzanie wyniku
        report.generateReport();
        String output = outContent.toString();

        assertFalse(output.contains("Multi-Year Worker"), "Employee should be in report");
        assertFalse(output.contains("4.00"), "Only 2025 hours should be counted");
        assertFalse(output.contains("6.00"), "2024 hours should NOT be counted");
    }

    @Test
    void testGenerateReport_withNoEmployees() {
        // Pusta lista pracowników
        DataModel dataModel = new DataModel(Collections.emptyList().toString());
        Report1 report = new Report1(2023, dataModel);

        report.generateReport();
        String output = outContent.toString();

        // Sprawdzenie, że raport ma nagłówek i stopkę
        assertTrue(output.contains("RAPORT: Report1"));
        assertTrue(output.contains("+=================")); // Nagłówek
        assertTrue(output.contains("+====================================================+")); // Stopka

        // Sprawdzenie, że nie zawiera żadnych danych pracowników
        assertFalse(output.contains("| 1")); // Nie ma żadnego ID wiersza z pracownikiem
    }

}
