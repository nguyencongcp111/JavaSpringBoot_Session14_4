package com.example.employeemanager.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.employeemanager.models.dto.request.EmployeeCreateRequest;
import com.example.employeemanager.models.entity.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import com.example.employeemanager.security.config.CloudinaryConfig;
import com.example.employeemanager.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Cloudinary cloudinary;

    public List<Employee> getEmployees() {
        List<Employee> employeeList = employeeRepository.findAll();

        employeeList.add(new Employee(1, "Nguyễn Thanh Tùng", 8000000, "IT", null));
        employeeList.add(new Employee(2, "Trần Quốc Tuấn", 8000000, "HR", null));
        employeeList.add(new Employee(3, "Trương Minh Huy", 8000000, "FM", null));

        return employeeList;
    }

    public Employee createEmployee (EmployeeCreateRequest employeeCreateRequest) throws IOException {
        MultipartFile avatar = employeeCreateRequest.getAvatarUrl();

        String fileName = UUID.randomUUID() + "." + avatar.getOriginalFilename();


        Map result = cloudinary.uploader().upload(
                avatar.getBytes(),
                ObjectUtils.emptyMap()
        );

        String imageUrl =
                result.get("secure_url").toString();

        Employee employee = Employee.builder()
                .fullName(employeeCreateRequest.getFullName())
                .salary(employeeCreateRequest.getSalary())
                .department(employeeCreateRequest.getDepartment())
                .avatarUrl(imageUrl)
                .build();

        return employeeRepository.save(employee);


    }
}
