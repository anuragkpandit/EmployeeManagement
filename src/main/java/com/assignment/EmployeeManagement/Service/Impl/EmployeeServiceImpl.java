package com.assignment.EmployeeManagement.Service.Impl;

import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Dto.Project;
import com.assignment.EmployeeManagement.Entity.Employee;
import com.assignment.EmployeeManagement.Entity.Office;
import com.assignment.EmployeeManagement.Entity.Skills;
import com.assignment.EmployeeManagement.Exception.ResourceNotFoundException;
import com.assignment.EmployeeManagement.External.Services.ProjectService;
import com.assignment.EmployeeManagement.Mapper.EmployeeMapper;
import com.assignment.EmployeeManagement.Repository.EmployeeRepository;
import com.assignment.EmployeeManagement.Service.EmployeeService;
import com.assignment.EmployeeManagement.Service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ProjectService projectService;

    @Override
    public String welcomeMessage() {
        return "Hello Employee";
    }

    @Override
    public EmployeeDto CreateEmployee(Employee employee) {
        employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        Project project = new Project();
//        project.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
        project.setProjectName("DATA");
        project.setClientName("Analysis");
        projectService.createProject(project);
        log.info("Employee data saved successfully");
        return employeeMapper.modelToDto(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDto updateEmployee(Long id, Employee employeeDetails) throws ResourceNotFoundException{


        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this Id:" + id));
        log.info("Employee id is present"+id);
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailId(employeeDetails.getEmailId());
        Skills skills = new Skills();
        skills.setSkill(employeeDetails.getSkills().getSkill());
        skills.setProficiency(employeeDetails.getSkills().getProficiency());
        employee.setSkills(skills);
        Office office = new Office();
        office.setOfficeLocation(employeeDetails.getOffice().getOfficeLocation());
        office.setSeatAllocation(employeeDetails.getOffice().getSeatAllocation());
        employee.setOffice(office);
        employee.setYearsOfExperience(employeeDetails.getYearsOfExperience());
        employee.setBirthDate(employeeDetails.getBirthDate());
        employee.setJoiningDate(employeeDetails.getJoiningDate());
        log.info("Employee updated successfully : "+id);
        return employeeMapper.modelToDto(employeeRepository.save(employee));
    }


    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.modelsToDtos(employeeRepository.findAll());
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
        log.info("Employee id is present"+id);
        return employeeMapper.modelToDto(employee);
    }

    @Override
    public void deleteEmployee(Long id) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("id not found : " + id));
        log.info("Employee id is present"+id);
        employeeRepository.delete(employee);
    }
}
