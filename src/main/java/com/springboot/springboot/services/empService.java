package com.springboot.springboot.services;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface empService {

    List<EmployeeDetails> getEmployees(int pageNo);
    EmployeeDetails getEmployee(Long empId);

    EmployeeDetails addEmployee(com.springboot.springboot.DTO.request.EmployeeDetails empName);

    ResponseEntity<String> deleteEmployee(long empId);

    ResponseEntity<String> updateEmployee(com.springboot.springboot.DTO.request.EmployeeDetails employeeDetails);
}
