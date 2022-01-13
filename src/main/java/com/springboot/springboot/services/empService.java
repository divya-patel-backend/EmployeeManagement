package com.springboot.springboot.services;

import com.springboot.springboot.entity.Employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface empService {

    List<Employee> getEmployees();
    Optional<Employee> getEmployee(Long emp_id);

    Employee addEmployee(String emp_name, Date date);

    void deleteEmployee(long parseLong);
}
