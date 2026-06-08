package com.example.employeemanager.controller;

import com.example.employeemanager.models.entity.Employees;
import com.example.employeemanager.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeesController {
    private final EmployeesService employeesService;

    @GetMapping
    public List<Employees> getEmployees () {
        return employeesService.getEmployees();
    }
}
