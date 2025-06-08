package org.example.Report;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfDocument;
import com.lowagie.text.pdf.PdfWriter;
import org.example.DataModel.DataModel;
import org.example.DataModel.Employee;
import org.example.DataModel.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Report1  implements IReport {

    private DataModel dataModel;
    private int year;

    public Report1(int year, DataModel dataModel) {
        this.year = year;
        this.dataModel = dataModel;
    }

    @Override
    public void generateReport() {
        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                if (task.getDate().getYear()==year) {
                    totalHours = totalHours + task.getHours();
                }
            }
            if (totalHours != 0) {
                System.out.println( employee.getName() + " " + totalHours);
            }
        }
    }

    public List<String> generateReportForExport () {
        List<String> reportLines = new ArrayList<>();

        for (Employee employee : dataModel.getEmployeesList()) {
            double totalHours = 0;
            for (Task task : employee.getTaskList()) {
                if (task.getDate().getYear() == year) {
                    totalHours += task.getHours();
                }
            }
            if (totalHours != 0) {
                reportLines.add(employee.getName() + ": " + totalHours + " godzin");
            }
        }

        return reportLines;
    }

    @Override
    public void exportReportToPdf(List<String> lines, String outputPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();

            for (String line : lines) {
                document.add(new Paragraph(line));
            }

            document.close();
            System.out.println("Zapisano PDF: " + outputPath);
        } catch (Exception e) {
            System.err.println("Błąd podczas zapisu PDF: " + e.getMessage());
        }
    }
}

