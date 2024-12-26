package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;
import com.numeroalpha.service.reportingdemo.exporter.impl.HtmlReportExporter;

public class HtmlReportExporterFactory implements ReportExporterAbstractFactory {

    @Override
    public ReportExporter createExporter() {
        return new HtmlReportExporter();
    }
}