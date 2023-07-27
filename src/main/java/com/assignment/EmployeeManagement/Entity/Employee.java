package com.assignment.EmployeeManagement.Entity;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Employee")
public class Employee {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private Long id;

    @Indexed(unique = true)
    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    @Email
    private String emailId;

    private Skills skills;

    private Office office;

    private Long yearsOfExperience;

    private LocalDate joiningDate;

    private LocalDate birthDate;
}
