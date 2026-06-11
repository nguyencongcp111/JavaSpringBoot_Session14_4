package com.example.employeemanager.controller;

import com.example.employeemanager.models.dto.request.EmployeeCreateRequest;
import com.example.employeemanager.models.dto.response.DataResponse;
import com.example.employeemanager.models.entity.Employee;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> getEmployees () {
        return employeeService.getEmployees();
    }

    @PostMapping
    public ResponseEntity<DataResponse<Employee>> createEmployee (
            @ModelAttribute EmployeeCreateRequest employeeCreateRequest
    ) throws IOException {
        return new ResponseEntity<>(
                new DataResponse<>(
                        HttpStatus.CREATED,
                        employeeService.createEmployee(employeeCreateRequest),
                        "Đã tạo nhân viên: " + employeeCreateRequest.getFullName()
                ), HttpStatus.CREATED
        );
    }
}
