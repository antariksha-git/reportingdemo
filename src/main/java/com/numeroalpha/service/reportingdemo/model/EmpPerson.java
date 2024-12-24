package com.numeroalpha.service.reportingdemo.model;

import com.numeroalpha.service.reportingdemo.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "empy_person")// Table name in the database
public class EmpPerson {

    @Id
    @Column(name = "uuid", nullable = false, length = 36)
    private String uuid;  // Mapping to varchar(36)

    @Column(name = "account_type", nullable = false)
    private int accountType;  // Mapping to tinyint

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;  // Mapping to datetime(6)

    @Column(name = "date_of_birth", nullable = true)
    private LocalDateTime dateOfBirth;  // Mapping to datetime(6), nullable

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;  // Mapping to datetime(6)

    @Column(name = "trace_id", nullable = true, length = 45)
    private String traceId;  // Mapping to varchar(45), nullable

    @Column(name = "created_by", nullable = false, length = 100)
    private String createdBy;  // Mapping to varchar(100)

    @Column(name = "updated_by", nullable = false, length = 100)
    private String updatedBy;  // Mapping to varchar(100)

    @Column(name = "account_number", nullable = false, length = 255)
    private String accountNumber;  // Mapping to varchar(255)

    @Column(name = "currency", nullable = false, length = 255)
    private String currency;  // Mapping to varchar(255)

    @Column(name = "employee_id", nullable = false, length = 255)
    private String employeeId;  // Mapping to varchar(255)

    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;  // Mapping to varchar(255)

    @Column(name = "ifsc_code", nullable = false, length = 255)
    private String ifscCode;  // Mapping to varchar(255)

    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;  // Mapping to varchar(255)

    @Column(name = "pan_number", nullable = false, length = 255)
    private String panNumber;  // Mapping to varchar(255)

    @Column(name = "payment_type", nullable = false, length = 255)
    private String paymentType;  // Mapping to varchar(255)

    @Column(name = "period", nullable = false, length = 255)
    private String period;  // Mapping to varchar(255)

    @Column(name = "uan_number", nullable = false, length = 255)
    private String uanNumber;  // Mapping to varchar(255)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
}