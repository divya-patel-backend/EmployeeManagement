package com.springboot.springboot.controller;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.entity.Employee;
import com.springboot.springboot.services.empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private empService empService;

    @GetMapping("/employees")
    public List<EmployeeDetails> getEmployees(){
        return this.empService.getEmployees();
    }

    @GetMapping("/employees/{emp_id}")
    public EmployeeDetails getEmployee(@PathVariable Long emp_id){
        return this.empService.getEmployee(emp_id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody com.springboot.springboot.DTO.request.EmployeeDetails employeeDetails){
        return this.empService.addEmployee(employeeDetails);
    }

    @PutMapping("/employees")
    public ResponseEntity<String> updateEmployee(@RequestBody com.springboot.springboot.DTO.request.EmployeeDetails employeeDetails){
        return this.empService.updateEmployee(employeeDetails);
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String empId){
        return this.empService.deleteEmployee(Long.parseLong(empId));
    }

}
