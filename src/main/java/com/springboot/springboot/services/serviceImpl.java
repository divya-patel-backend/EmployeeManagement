package com.springboot.springboot.services;

import com.springboot.springboot.Dao.empDao;
import com.springboot.springboot.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class serviceImpl implements empService{

    @Autowired
    private empDao empDao;

    //Get Employee
    @Override
    public List<Employee> getEmployees() {
        return empDao.findAll();
    }

    //Get Employee By Id
    @Override
    public Optional<Employee> getEmployee(Long emp_id) {
        return empDao.findById(emp_id);
    }

    //Add Employee Detail
    @Override
    public Employee addEmployee(String emp_name, Date date) {
        Employee e=new Employee(emp_name,date+"");
        empDao.save(e);
        return e;
    }

    //Delete Employee detail by Id
    @Override
    public void deleteEmployee(long parseLong) {
            Employee entity=empDao.getById(parseLong);
            empDao.delete(entity);
    }
}
