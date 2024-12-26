package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import com.numeroalpha.service.reportingdemo.exporter.impl.XlsReportExporter;

public class XlsReportExporterFactory implements ReportExporterAbstractFactory {

    @Override
    public ReportExporter createExporter() {
        return new XlsReportExporter();
    }
}