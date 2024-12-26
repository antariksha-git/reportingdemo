package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import com.numeroalpha.service.reportingdemo.exporter.impl.PdfReportExporter;

public class PdfReportExporterFactory implements ReportExporterAbstractFactory {

    @Override
    public ReportExporter createExporter() {
        return new PdfReportExporter();
    }
}