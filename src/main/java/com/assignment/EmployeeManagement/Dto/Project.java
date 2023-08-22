package com.assignment.EmployeeManagement.Dto;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    private long id;

    private String projectName;

    private String clientName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String duration;

    private List<String> skills;

    private String location;
}
