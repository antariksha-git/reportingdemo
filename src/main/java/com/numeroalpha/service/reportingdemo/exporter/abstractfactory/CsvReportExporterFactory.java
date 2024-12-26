package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import com.numeroalpha.service.reportingdemo.exporter.impl.CsvReportExporter;

public class CsvReportExporterFactory implements ReportExporterAbstractFactory {

    @Override
    public ReportExporter createExporter() {
        return new CsvReportExporter();
    }
}