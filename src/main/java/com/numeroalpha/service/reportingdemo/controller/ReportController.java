package com.numeroalpha.service.reportingdemo.controller;

import com.numeroalpha.service.reportingdemo.service.impl.ReportServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/report")
@Slf4j
public class ReportController {

    @Autowired
    private ReportServiceImpl reportService;

    @GetMapping("/{format}")
    public ResponseEntity<byte[]> generateReport(@PathVariable String format) {
        try {
            // Generate the report in the specified format
            byte[] report = reportService.generateReport(format);

            // Set the content type and disposition based on the format
            HttpHeaders headers = new HttpHeaders();
            switch (format.toUpperCase()) {
                case "PDF":
                    headers.setContentType(MediaType.APPLICATION_PDF);
                    headers.setContentDispositionFormData("inline", "employee_report.pdf");
                    break;
                case "HTML":
                    headers.setContentType(MediaType.TEXT_HTML);
                    break;
                case "XLS":
                    headers.setContentType(MediaType.valueOf("application/vnd.ms-excel"));
                    headers.setContentDispositionFormData("inline", "employee_report.xls");
                    break;
                case "CSV":
                    headers.setContentType(MediaType.valueOf("text/csv"));
                    headers.setContentDispositionFormData("inline", "employee_report.csv");
                    break;
                default:
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(report);

        } catch (Exception e) {
            log.error("Error generating report: {}", e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
