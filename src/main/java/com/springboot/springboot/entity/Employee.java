package com.springboot.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
    @Id @GeneratedValue()
    private long emp_id;


    private String emp_name;


    private String emp_doj;

    public Employee(String emp_name,String emp_doj) {
        this.emp_name = emp_name;
        this.emp_doj=emp_doj;
    }

    public Employee() {

    }

    public long getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(long emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_doj() {
        return emp_doj;
    }

    public void setEmp_doj(String emp_doj) {
        this.emp_doj = emp_doj;
    }
}
