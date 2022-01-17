package com.springboot.springboot.services;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.Dao.empDao;
import com.springboot.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<EmployeeDetails> getEmployees() {
//        return empDao.findAll();
        List<EmployeeDetails> employeeDetailsList=new LinkedList<>();
        for(Employee employee:empDao.findAll()){
            EmployeeDetails employeeDetails=new EmployeeDetails();
            employeeDetails.setEmpId(employee.getEmpId());
            employeeDetails.setEmpDate(employee.getEmpDoj());
            employeeDetails.setEmpName(employee.getEmpName());
            employeeDetails.setCreatedAt(employee.getEmpCreatedAt());
            employeeDetails.setUpdatedAt(employee.getEmpUpdatedAt());
            employeeDetailsList.add(employeeDetails);
        }
        return employeeDetailsList;
    }

    //Get Employee By Id
    @Override
    public EmployeeDetails getEmployee(Long emp_id) {
//        return empDao.findById(emp_id);
        EmployeeDetails  emp=new EmployeeDetails();
        Employee employee=new Employee();
         employee=empDao.getById(emp_id);
            emp.setEmpId(employee.getEmpId());
            emp.setEmpName(employee.getEmpName());
            emp.setEmpDate(employee.getEmpDoj());
            emp.setCreatedAt(employee.getEmpCreatedAt());
            emp.setUpdatedAt(employee.getEmpUpdatedAt());
            return emp;
    }

    //Add Employee Detail
    @Override
    public Employee addEmployee(com.springboot.springboot.DTO.request.EmployeeDetails emp_name) {
        LocalDate date=LocalDate.now();
        LocalDateTime updatedAt= LocalDateTime.now();
        LocalDateTime createdAt=LocalDateTime.now();
        Employee emp=new Employee(emp_name.getEmpName(),date,createdAt,updatedAt);
        empDao.save(emp);
        return emp;
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
    public ResponseEntity<String> updateEmployee(com.springboot.springboot.DTO.request.EmployeeDetails e) {
        if(empDao.existsById(e.getEmpId())) {
            LocalDateTime updatedAt = LocalDateTime.now();
            Employee emp = empDao.getById(e.getEmpId());
            emp.setEmpUpdatedAt(updatedAt);
            emp.setEmpName(e.getEmpName());
            empDao.save(emp);
            return ResponseEntity.status(HttpStatus.OK).body("Employee updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
    }

}
