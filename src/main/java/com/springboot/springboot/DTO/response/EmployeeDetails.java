package com.springboot.springboot.DTO.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmployeeDetails {
    private long empId;
    private String empName;
    private LocalDate empDate;

    public EmployeeDetails() {

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

    public LocalDate getEmpDate() {
        return empDate;
    }

    public void setEmpDate(LocalDate empDate) {
        this.empDate = empDate;
    }


}
