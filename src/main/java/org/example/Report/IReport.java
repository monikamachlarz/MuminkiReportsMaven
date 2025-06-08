package org.example.Report;

import java.util.List;

public interface IReport {

    void generateReport();
  
    void exportReportToPdf(List<String> lines, String outputPath);

    public List<String> generateReportForExport ();
}
