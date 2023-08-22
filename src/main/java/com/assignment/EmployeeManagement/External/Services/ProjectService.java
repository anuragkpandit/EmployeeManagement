package com.assignment.EmployeeManagement.External.Services;

import com.assignment.EmployeeManagement.Dto.Project;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="projectService", url = "http://localhost:8082/api")
public interface ProjectService {

    @GetMapping("/projects")
    List<Project> getAllProjects();

    @PostMapping(value = "/project")
    Project createProject(@RequestBody Project project);
}
