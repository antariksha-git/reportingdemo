package com.numeroalpha.service.reportingdemo.exporter.impl;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleCsvReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;

import java.io.OutputStream;

public class CsvReportExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, OutputStream outputStream, String fileName) throws Exception {
        // Create a CSV Exporter instance
        JRCsvExporter csvExporter = new JRCsvExporter();

        // Set the exporter input (JasperPrint object)
        csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        // Set the exporter output to OutputStream
        csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));

        // Configure the CSV Exporter
        SimpleCsvReportConfiguration reportConfig = new SimpleCsvReportConfiguration();

        // Apply the CSV report configuration
        csvExporter.setConfiguration(reportConfig);

        // Export the report
        csvExporter.exportReport();

        System.out.println("CSV report exported successfully with file name: " + fileName);
    }
}