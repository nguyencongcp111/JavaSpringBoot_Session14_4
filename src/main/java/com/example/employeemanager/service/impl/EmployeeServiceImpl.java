package com.example.employeemanager.service.impl;

import com.example.employeemanager.models.entity.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        employeeList.add(new Employee(1, "Nguyễn Thanh Tùng", 8000000));
        employeeList.add(new Employee(2, "Trần Quốc Tuấn", 8000000));
        employeeList.add(new Employee(3, "Trương Minh Huy", 8000000));

        return employeeList;
    }
}
