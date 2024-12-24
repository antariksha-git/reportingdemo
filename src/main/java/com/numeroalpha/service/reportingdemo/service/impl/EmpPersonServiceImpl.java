package com.numeroalpha.service.reportingdemo.service.impl;

import com.numeroalpha.service.reportingdemo.model.EmpPerson;
import com.numeroalpha.service.reportingdemo.repo.EmpPersonRepository;
import com.numeroalpha.service.reportingdemo.service.EmpPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpPersonServiceImpl implements EmpPersonService {

    private final EmpPersonRepository empPersonRepository;

    @Autowired
    public EmpPersonServiceImpl(EmpPersonRepository empPersonRepository) {
        this.empPersonRepository = empPersonRepository;
    }

    @Override
    public List<EmpPerson> getAllEmpPersons() {
        return empPersonRepository.findAll();
    }
}
