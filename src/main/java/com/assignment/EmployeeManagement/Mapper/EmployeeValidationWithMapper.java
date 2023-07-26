package com.assignment.EmployeeManagement.Mapper;

import com.assignment.EmployeeManagement.Exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class EmployeeValidationWithMapper{

    public Long EmployeeValidationWithMapper(long id) throws ResourceNotFoundException{
        if (id == -1){
            throw new ResourceNotFoundException("-1 id is not allowed");
        }
        return id;
    }
}
