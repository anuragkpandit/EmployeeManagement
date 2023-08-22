package com.assignment.EmployeeManagement.Controller;


import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Dto.Project;
import com.assignment.EmployeeManagement.Entity.Employee;
import com.assignment.EmployeeManagement.Exception.ResourceNotFoundException;
import com.assignment.EmployeeManagement.External.Services.ProjectService;
import com.assignment.EmployeeManagement.Service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = {"/api/v1"}, produces = APPLICATION_JSON_VALUE)
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;



    @Operation(summary = "welcome API")
    @GetMapping("/welcome")
    public String welcomeMessage() {
        log.info("Get Employee list");
        return employeeService.welcomeMessage();
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
    @Operation(summary = "Get an Employee by its id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the Employee", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeDto.class))}),
            @ApiResponse(responseCode = "404", description = "Employee not found", content = @Content)})
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
