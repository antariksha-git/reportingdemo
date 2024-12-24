package com.numeroalpha.service.reportingdemo.reportdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmpPersonReportDto {
    private String employeeId;
    private String accountNumber;
    private String firstName;
    private String lastName;
}
