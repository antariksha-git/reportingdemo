package com.numeroalpha.service.reportingdemo.exporter.abstractfactory;

public class ReportExporterFactoryProducer {

    public static ReportExporterAbstractFactory getFactory(String format) {
        switch (format.toUpperCase()) {
            case "PDF":
                return new PdfReportExporterFactory();
            case "HTML":
                return new HtmlReportExporterFactory();
            case "XLS":
                return new XlsReportExporterFactory();
            case "CSV":
                return new CsvReportExporterFactory();
            default:
                throw new IllegalArgumentException("Unsupported report format: " + format);
        }
    }
}