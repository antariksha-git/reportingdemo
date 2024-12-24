package com.numeroalpha.service.reportingdemo.controller;

import com.numeroalpha.service.reportingdemo.model.EmpPerson;
import com.numeroalpha.service.reportingdemo.service.EmpPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/payroll")
public class EmpPersonController {

    private final EmpPersonService empPersonService;

    @Autowired
    public EmpPersonController(EmpPersonService empPersonService) {
        this.empPersonService = empPersonService;
    }

    @GetMapping
    public List<EmpPerson> getAllEmpPersons() {
        return empPersonService.getAllEmpPersons();
    }
}

