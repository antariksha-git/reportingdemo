package com.numeroalpha.service.reportingdemo.exporter.impl;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;

import java.io.OutputStream;

public class HtmlReportExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, OutputStream outputStream, String fileName) throws Exception {
        HtmlExporter htmlExporter = new HtmlExporter();
        htmlExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(outputStream));
        
        SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
        htmlExporter.setConfiguration(configuration);
        htmlExporter.exportReport();
        System.out.println("HTML report exported successfully with file name: " + fileName);
    }
}