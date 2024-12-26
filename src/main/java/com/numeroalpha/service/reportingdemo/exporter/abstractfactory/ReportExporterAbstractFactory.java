package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

import com.numeroalpha.service.reportingdemo.exporter.ReportExporter;

public interface ReportExporterAbstractFactory {
    ReportExporter createExporter();
}