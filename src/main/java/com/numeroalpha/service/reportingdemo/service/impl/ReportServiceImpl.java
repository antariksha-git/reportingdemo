package com.numeroalpha.service.reportingdemo.service.impl;

import com.numeroalpha.service.reportingdemo.repo.EmpPersonRepository;
import com.numeroalpha.service.reportingdemo.reportdto.EmpPersonReportDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.poi.export.JRXlsExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl {

    @Autowired
    private EmpPersonRepository empyPersonRepository;

    public byte[] generateReport(String format) throws Exception {
        // Load and compile the .jrxml file
        InputStream reportStream = getClass().getResourceAsStream("/empyPersonReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Fetch employee data from DB
        List<EmpPersonReportDto> employees = empyPersonRepository.findActivePersons();

        // Map data to JasperReports format
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        // Fill the report
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Spring Boot App");

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to the requested format
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        switch (format.toUpperCase()) {
            case "PDF":
                JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
                break;
            case "HTML":
                //JasperExportManager.exportReportToHtmlStream(jasperPrint, outputStream);
                break;
            case "XLS":
                JRXlsExporter xlsExporter = new JRXlsExporter();
                xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                xlsExporter.exportReport();
                break;
            case "CSV":
                JRCsvExporter csvExporter = new JRCsvExporter();
                csvExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                csvExporter.setExporterOutput(new SimpleWriterExporterOutput(outputStream));
                csvExporter.exportReport();
                break;
            default:
                throw new IllegalArgumentException("Unsupported report format: " + format);
        }

        // Return the generated report as a byte array
        return outputStream.toByteArray();
    }
}
