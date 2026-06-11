package com.example.employeemanager.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeCreateRequest {
    private String fullName;

    private double salary;

    private String department;

    private MultipartFile avatarUrl;
}
