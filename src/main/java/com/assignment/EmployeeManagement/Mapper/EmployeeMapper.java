package com.assignment.EmployeeManagement.Mapper;


import com.assignment.EmployeeManagement.Dto.EmployeeDto;
import com.assignment.EmployeeManagement.Entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = EmployeeValidationWithMapper.class,componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto modelToDto(Employee employee);


    List<EmployeeDto> modelsToDtos(List<Employee> employees);

    public Employee dtoToModel(EmployeeDto employeeDto);

}
