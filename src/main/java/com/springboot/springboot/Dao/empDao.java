package com.springboot.springboot.Dao;

import com.springboot.springboot.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empDao extends JpaRepository<Employee,Long> {

}
