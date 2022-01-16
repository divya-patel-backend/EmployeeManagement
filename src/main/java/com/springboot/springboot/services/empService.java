package com.springboot.springboot.services;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface empService {

    List<EmployeeDetails> getEmployees();
    EmployeeDetails getEmployee(Long emp_id);

    Employee addEmployee(com.springboot.springboot.DTO.request.EmployeeDetails emp_name);

    ResponseEntity<String> deleteEmployee(long parseLong);

    ResponseEntity<String> updateEmployee(com.springboot.springboot.DTO.request.EmployeeDetails e);
}
