package com.numeroalpha.service.reportingdemo.service.impl;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import com.numeroalpha.service.reportingdemo.exporter.abstractfactory.ReportExporterAbstractFactory;
import com.numeroalpha.service.reportingdemo.exporter.abstractfactory.ReportExporterFactoryProducer;
import com.numeroalpha.service.reportingdemo.repo.EmpPersonRepository;
import com.numeroalpha.service.reportingdemo.reportdto.EmpPersonReportDto;
import com.numeroalpha.service.reportingdemo.service.ReportService;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpPersonRepository empPersonRepository;

    public byte[] generateReport(String format, String fileName) throws Exception {
        // Compile the report file
        InputStream reportStream = getClass().getResourceAsStream("/empyPersonReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Fetch data and wrap it in a JasperReports datasource
        List<EmpPersonReportDto> employees = empPersonRepository.findActivePersons();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);

        // Fill the report with parameters and data
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Spring Boot App");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Use the Abstract Factory Design Pattern to get the appropriate exporter
        ReportExporterAbstractFactory factory = ReportExporterFactoryProducer.getFactory(format);
        ReportExporter exporter = factory.createExporter();

        // Export the report
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        exporter.export(jasperPrint, outputStream, fileName);

        return outputStream.toByteArray();
    }
}