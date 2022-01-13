package com.springboot.springboot.controller;

import com.springboot.springboot.entity.Employee;
import com.springboot.springboot.services.empService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.springboot.springboot.services.empService;
import org.springframework.web.servlet.resource.HttpResource;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private empService empService;

    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return this.empService.getEmployees();
    }

    @GetMapping("/employees/{emp_id}")
    public Optional<Employee> getEmployee(@PathVariable Long emp_id){
        return this.empService.getEmployee(emp_id);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee e){
        Date date=new Date();
        return this.empService.addEmployee(e.getEmp_name(),date);
    }

    @DeleteMapping("/employees/{empId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable String empId){
        try {
            this.empService.deleteEmployee(Long.parseLong(empId));
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
