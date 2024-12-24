package com.numeroalpha.service.reportingdemo.repo;

import com.numeroalpha.service.reportingdemo.model.EmpPerson;
import com.numeroalpha.service.reportingdemo.reportdto.EmpPersonReportDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpPersonRepository extends JpaRepository<EmpPerson, String> {
    @Query("SELECT new com.numeroalpha.service.reportingdemo.reportdto.EmpPersonReportDto(p.employeeId, p.accountNumber, p.firstName, p.lastName) " +
            "FROM EmpPerson p WHERE p.status = 'ACTIVE'")
    List<EmpPersonReportDto> findActivePersons();
}

