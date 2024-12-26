package com.numeroalpha.service.reportingdemo.exporter.impl;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.io.OutputStream;

public class XlsReportExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, OutputStream outputStream, String fileName) throws Exception {
        // Create an XLSX Exporter instance
        JRXlsxExporter xlsxExporter = new JRXlsxExporter();

        // Set the exporter input (JasperPrint object)
        xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));

        // Set the exporter output (OutputStream for the Excel file)
        xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

        // Basic configuration for plain Excel output
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setIgnoreCellBorder(true);    // Ignore cell borders from the design
        configuration.setIgnoreGraphics(true);     // Ignore graphics (e.g., images, boxes)
        configuration.setWhitePageBackground(false); // Disable white background
        configuration.setRemoveEmptySpaceBetweenRows(true); // Remove extra spaces
        configuration.setDetectCellType(true);     // Automatically detect cell types (numbers, strings, etc.)

        // Apply the simplified configuration
        xlsxExporter.setConfiguration(configuration);

        // Export the report with plain formatting
        xlsxExporter.exportReport();

        System.out.println("Plain Excel report exported successfully with file name: " + fileName);
    }
}