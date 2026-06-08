package com.example.employeemanager.service.impl;

import com.example.employeemanager.models.entity.Employees;
import com.example.employeemanager.repository.EmployeesRepository;
import com.example.employeemanager.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeesService {
    private final EmployeesRepository employeesRepository;

    public List<Employees> getEmployees() {
        List<Employees> employeesList = employeesRepository.findAll();

        employeesList.add(new Employees(1, "Nguyễn Thanh Tùng", 8000000));
        employeesList.add(new Employees(2, "Trần Quốc Tuấn", 8000000));
        employeesList.add(new Employees(3, "Trương Minh Huy", 8000000));

        return employeesList;
    }
}
