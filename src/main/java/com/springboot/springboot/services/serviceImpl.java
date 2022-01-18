package com.springboot.springboot.services;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.Dao.empDao;
import com.springboot.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class serviceImpl implements empService{

    @Autowired
    private empDao empDao;

    //Get Employee
    @Override
    public List<EmployeeDetails> getEmployees(int pageNo) {
        List<EmployeeDetails> employeeDetailsList=new LinkedList<>();
        Pageable page= PageRequest.of(pageNo,3);
        for(Employee employee:empDao.findAll(page)){
            EmployeeDetails employeeDetails=new EmployeeDetails();
            employeeDetails.setEmpId(employee.getEmpId());
            employeeDetails.setEmpDate(employee.getEmpDoj());
            employeeDetails.setEmpName(employee.getEmpName());
            employeeDetailsList.add(employeeDetails);
        }
        return employeeDetailsList;
    }

    //Get Employee By Id
    @Override
    public EmployeeDetails getEmployee(Long emp_id) {
        EmployeeDetails  employeeDetails=new EmployeeDetails();
        Employee employee=new Employee();
         employee=empDao.getById(emp_id);
            employeeDetails.setEmpId(employee.getEmpId());
            employeeDetails.setEmpName(employee.getEmpName());
            employeeDetails.setEmpDate(employee.getEmpDoj());
            return employeeDetails;
    }

    //Add Employee Detail
    @Override
    public EmployeeDetails addEmployee(com.springboot.springboot.DTO.request.EmployeeDetails employeeDetails) {
        LocalDate date=LocalDate.now();
        LocalDateTime updatedAt= LocalDateTime.now();
        LocalDateTime createdAt=LocalDateTime.now();
        Employee emp=new Employee(employeeDetails.getEmpName(),date,createdAt,updatedAt);
        Employee emp1=empDao.save(emp);
        EmployeeDetails employeeDetails1=new EmployeeDetails();
        employeeDetails1.setEmpName(emp1.getEmpName());
        employeeDetails1.setEmpDate(emp1.getEmpDoj());
        employeeDetails1.setEmpId(emp1.getEmpId());
        return employeeDetails1;
    }

    //Delete Employee detail by Id
    @Override
    public ResponseEntity<String> deleteEmployee(long empId) {
        if(empDao.existsById(empId)){
            Employee entity=empDao.getById(empId);
            empDao.delete(entity);
            return ResponseEntity.status(HttpStatus.OK).body("Employee deleted Successfull");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Empolyee not found");

    }

    @Override
    public ResponseEntity<String> updateEmployee(com.springboot.springboot.DTO.request.EmployeeDetails employee) {
        if(empDao.existsById(employee.getEmpId())) {
            LocalDateTime updatedAt = LocalDateTime.now();
            Employee emp = empDao.getById(employee.getEmpId());
            emp.setEmpUpdatedAt(updatedAt);
            emp.setEmpName(employee.getEmpName());
            empDao.save(emp);
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }

}
