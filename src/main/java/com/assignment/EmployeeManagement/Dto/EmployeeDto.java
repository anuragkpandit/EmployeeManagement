package com.assignment.EmployeeManagement.Dto;

import com.assignment.EmployeeManagement.Entity.Office;
import com.assignment.EmployeeManagement.Entity.Skills;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;



import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDto {

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
