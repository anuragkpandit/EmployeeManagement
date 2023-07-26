package com.assignment.EmployeeManagement.Service;

import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Entity.Employee;
import com.assignment.EmployeeManagement.Exception.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {

    EmployeeDto CreateEmployee(Employee employee);

    EmployeeDto updateEmployee(Long id, Employee employeeDetails) throws ResourceNotFoundException;

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException;

    void deleteEmployee(Long id) throws ResourceNotFoundException;
}
