package com.numeroalpha.service.reportingdemo.exporter.impl;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.OutputStream;

public class PdfReportExporter implements ReportExporter {

    @Override
    public void export(JasperPrint jasperPrint, OutputStream outputStream, String fileName) throws Exception {
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        System.out.println("PDF report exported successfully with file name: " + fileName);
    }
}