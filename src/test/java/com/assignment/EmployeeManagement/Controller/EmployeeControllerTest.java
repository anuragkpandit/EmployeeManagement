package com.assignment.EmployeeManagement.Controller;

import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Entity.Employee;
import com.assignment.EmployeeManagement.Entity.Office;
import com.assignment.EmployeeManagement.Entity.Skills;
import com.assignment.EmployeeManagement.Service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @Test
    public void shouldReturnDefaultMessage() {
        when(employeeService.welcomeMessage()).thenReturn("Hello Employee");

        String response = employeeController.welcomeMessage();
        assertThat(response).isEqualTo("Hello Employee");
    }

    @Test
    public void testCreateEmployee() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Anurag");
        employeeDto.setLastName("Pandit");


        Skills skills = new Skills("", "");
        Office office = new Office("", "");
        Employee employee = new Employee(null, "Anurag", "Pandit", "", skills, office, null, null, null);

        when(employeeService.CreateEmployee(employee)).thenReturn(employeeDto);

        ResponseEntity<EmployeeDto> responseEntity = employeeController.createEmployee(employee);
        assertThat(responseEntity.getBody().getFirstName()).isEqualTo("Anurag");
        assertThat(responseEntity.getBody().getLastName()).isEqualTo("Pandit");
    }

}