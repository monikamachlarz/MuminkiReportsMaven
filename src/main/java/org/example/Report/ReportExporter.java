package org.example.Report;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ReportExporter {

    public static void exportToPdf(String reportText, String outputPath) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            document.add(new Paragraph(reportText));
            document.close();
            System.out.println("Zapisano PDF: " + outputPath);
        } catch (Exception e) {
            System.err.println("Błąd podczas zapisu PDF: " + e.getMessage());
        }
    }

    public static void exportToXlsx(String reportText, String outputPath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Raport");

            String[] lines = reportText.split("\n");
            for (int i = 0; i < lines.length; i++) {
                Row row = sheet.createRow(i);
                String[] cells = lines[i].split("\t|: "); // rozdzielanie tekstu
                for (int j = 0; j < cells.length; j++) {
                    row.createCell(j).setCellValue(cells[j]);
                }
            }

            try (FileOutputStream out = new FileOutputStream(outputPath)) {
                workbook.write(out);
                System.out.println("Zapisano XLSX: " + outputPath);
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisu XLSX: " + e.getMessage());
        }
    }
}

