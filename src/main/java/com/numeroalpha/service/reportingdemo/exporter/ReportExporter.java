package com.numeroalpha.service.reportingdemo.exporter;

import net.sf.jasperreports.engine.JasperPrint;

import java.io.OutputStream;

public interface ReportExporter {
    void export(JasperPrint jasperPrint, OutputStream outputStream, String fileName) throws Exception;
}