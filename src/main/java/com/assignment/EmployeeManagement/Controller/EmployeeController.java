package com.assignment.EmployeeManagement.Controller;


import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Entity.Employee;
import com.assignment.EmployeeManagement.Exception.ResourceNotFoundException;
import com.assignment.EmployeeManagement.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String welcomeMessage() {
        log.info("Get Employee list");
        return "welcome to Employee service";
    }

    //Add
    @PostMapping("/employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.CreateEmployee(employee), HttpStatus.CREATED);
    }

    //Get
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        log.info("Get Employee list");
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeDetails(@Valid @PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {

        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }


    //Update
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {

        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeDetails));
    }

    //Delete
    @DeleteMapping("/employee/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        log.info("Employee deleted successfully : "+employeeId);
        return response;
    }
}
