package com.springboot.springboot.DTO.request;

public class EmployeeDetails {
    private String empName;
    private long empId;

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

    public EmployeeDetails() {

    }
}
