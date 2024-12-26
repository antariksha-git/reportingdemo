package com.numeroalpha.service.reportingdemo.service;

public interface ReportService {
    byte[] generateReport(String format, String fileName) throws Exception;
}