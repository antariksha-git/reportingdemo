package com.numeroalpha.service.reportingdemo.service.impl;

import com.numeroalpha.service.reportingdemo.repo.EmpPersonRepository;
import com.numeroalpha.service.reportingdemo.reportdto.EmpPersonReportDto;
import com.opencsv.CSVWriter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.poi.export.JRXlsExporter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
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
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("Employees");

                // Create header row
                Row headerRow = sheet.createRow(0);
                String[] headers = {"Employee Id", "Account Number", "First Name", "Last Name"};
                for (int i = 0; i < headers.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(headers[i]);
                    CellStyle style = workbook.createCellStyle();
                    Font font = workbook.createFont();
                    font.setBold(true);
                    style.setFont(font);
                    cell.setCellStyle(style);
                }

                // Populate data rows
                int rowNum = 1;
                for (EmpPersonReportDto employee : employees) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(employee.getEmployeeId());
                    row.createCell(1).setCellValue(employee.getAccountNumber());
                    row.createCell(2).setCellValue(employee.getFirstName());
                    row.createCell(3).setCellValue(employee.getLastName());
                }

                workbook.write(outputStream);
                workbook.close();
                break;
            case "CSV":
                CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(outputStream));
                String[] csvHeaders = {"Employee Id", "Account Number", "First Name", "Last Name"};
                csvWriter.writeNext(csvHeaders);

                for (EmpPersonReportDto employee : employees) {
                    String[] data = {
                            String.valueOf(employee.getEmployeeId()),
                            employee.getAccountNumber(),
                            employee.getFirstName(),
                            employee.getLastName()
                    };
                    csvWriter.writeNext(data);
                }

                csvWriter.close();
                break;
            default:
                throw new IllegalArgumentException("Unsupported report format: " + format);
        }

        // Return the generated report as a byte array
        return outputStream.toByteArray();
    }
}