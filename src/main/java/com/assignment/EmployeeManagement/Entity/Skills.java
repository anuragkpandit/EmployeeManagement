package com.assignment.EmployeeManagement.Entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skills {

    private String skill;

    private String proficiency;
}
