package com.springboot.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Employee {
    @Id @GeneratedValue()
    private long empId;
    private String empName;
    private LocalDate empDoj;
    private LocalDateTime empUpdatedAt;
    private LocalDateTime empCreatedAt;

    public LocalDateTime getEmpCreatedAt() {
        return empCreatedAt;
    }

    public void setEmpCreatedAt(LocalDateTime empCreatedAt) {
        this.empCreatedAt = empCreatedAt;
    }

    public LocalDateTime getEmpUpdatedAt() {
        return empUpdatedAt;
    }

    public void setEmpUpdatedAt(LocalDateTime empUpdatedAt) {
        this.empUpdatedAt = empUpdatedAt;
    }


    public Employee(String emp_name, LocalDate emp_doj,LocalDateTime emp_createdAt,LocalDateTime emp_updatedAt) {
        this.empName = emp_name;
        this.empDoj =emp_doj;
        this.empCreatedAt =emp_createdAt;
        this.empUpdatedAt =emp_updatedAt;
    }

    public Employee() {

    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public LocalDate getEmpDoj() {
        return empDoj;
    }

    public void setEmpDoj(LocalDate empDoj) {
        this.empDoj = empDoj;
    }
}
