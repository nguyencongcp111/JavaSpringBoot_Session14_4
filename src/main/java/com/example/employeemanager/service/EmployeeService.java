package com.example.employeemanager.service;

import com.example.employeemanager.models.dto.request.EmployeeCreateRequest;
import com.example.employeemanager.models.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee createEmployee (EmployeeCreateRequest employeeCreateRequest) throws IOException;
}
