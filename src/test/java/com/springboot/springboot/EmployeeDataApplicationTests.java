package com.springboot.springboot;

import com.springboot.springboot.DTO.response.EmployeeDetails;
import com.springboot.springboot.Dao.empDao;
import com.springboot.springboot.entity.Employee;
import com.springboot.springboot.services.serviceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EmployeeDataApplicationTests {

	@Mock
    private empDao employeeDaoMock;

    @InjectMocks
    private serviceImpl employeeServiceImplementation;

    @Test
    public void givenEmployeeId_WhenDeleteRequestisRaise_thanItshouldBeDeletedFromDatabase()
    {
        long employeeResignedId=5;
        ArgumentCaptor<Long> employeeIdCapturer = ArgumentCaptor.forClass(Long.class);
        when(employeeDaoMock.existsById(employeeResignedId)).thenReturn(true);

        employeeServiceImplementation.deleteEmployee(employeeResignedId);
        verify(employeeDaoMock,times(1)).getById(employeeIdCapturer.capture());
        assertEquals(employeeResignedId, employeeIdCapturer.getValue());
    }

    @Test
    public void givenPageNo_WhengetRequestisRaise_thanItshouldBegetFromDatabase(){

         int pageNo=1;
        LocalDate date=LocalDate.now();
        LocalDateTime dateTime=LocalDateTime.now();
        List<Employee> employeeList=new LinkedList<>();
        for(int i=0;i<5;i++){
            employeeList.add(new Employee(i,"xyz"+i,date,dateTime,dateTime));
        }
        List<EmployeeDetails> employeeDetailsList=new LinkedList<>();
        for(Employee employee:employeeList){
            EmployeeDetails employeeDetails=new EmployeeDetails();
            employeeDetails.setEmpId(employee.getEmpId());
            employeeDetails.setEmpName(employee.getEmpName());
            employeeDetails.setEmpDate(employee.getEmpDoj());
            employeeDetailsList.add(employeeDetails);
        }

        Pageable page=PageRequest.of(pageNo,3);
        Page<Employee> employeesPage=new PageImpl<>(employeeList);
        when(employeeDaoMock.findAll(page)).thenReturn(employeesPage);
        List<EmployeeDetails> employee=employeeServiceImplementation.getEmployees(pageNo);

        for(int i=0;i<5;i++) {
            assertEquals(employeeDetailsList.get(i).getEmpId(), employee.get(i).getEmpId());
            assertEquals(employeeDetailsList.get(i).getEmpName(), employee.get(i).getEmpName());
            assertEquals(employeeDetailsList.get(i).getEmpDate(), employee.get(i).getEmpDate());
        }


    }


    @Test
    public void givenEmployeeId_WhengetRequestisRaise_thanItshouldBegetFromDatabase(){
        long employeeId =1;

        LocalDate date = LocalDate.now();
        LocalDateTime dateTime =LocalDateTime.now();

        Employee employee = new Employee("divya",date,dateTime,dateTime);
        employee.setEmpId(employeeId);

        when(employeeDaoMock.getById(employeeId)).thenReturn(employee);
        EmployeeDetails actual = employeeServiceImplementation.getEmployee(employeeId);

        assertEquals(employee.getEmpDoj(),actual.getEmpDate());
        assertEquals(employee.getEmpId(),actual.getEmpId());
        assertEquals(employee.getEmpName(),actual.getEmpName());

    }

    @Test
    public void givenEmployeeId_WhenUpdateRequestisRaise_thanItshouldBeUpdatedFromDatabase(){
        long employeeId=5;
        com.springboot.springboot.DTO.request.EmployeeDetails emp = new com.springboot.springboot.DTO.request.EmployeeDetails();
        emp.setEmpId(employeeId);
        emp.setEmpName("patel");

        LocalDate date=LocalDate.now();
        LocalDateTime datetime=LocalDateTime.now();
        Employee employee=new Employee("divya",date,datetime,datetime);
        employee.setEmpId(employeeId);

        ArgumentCaptor<Employee> employee1 = ArgumentCaptor.forClass(Employee.class);

        when(employeeDaoMock.existsById(employeeId)).thenReturn(true);
        when(employeeDaoMock.getById(employeeId)).thenReturn(employee);

        ResponseEntity<String> actual=employeeServiceImplementation.updateEmployee(emp);

        verify(employeeDaoMock,times(1)).save(employee1.capture());
        ResponseEntity<String> expected=ResponseEntity.status(HttpStatus.OK).body("Employee updated");
        assertEquals(expected,actual);
    }

    @Test
    public void givenEmployeeId_WhenaddRequestisRaise_thanItshouldBeAddInDatabase(){
        com.springboot.springboot.DTO.request.EmployeeDetails employeeDetails=new com.springboot.springboot.DTO.request.EmployeeDetails();
        employeeDetails.setEmpName("Divine");
        employeeDetails.setEmpId(1);

        LocalDate date=LocalDate.now();
        LocalDateTime datetime=LocalDateTime.now();
        Employee emp=new Employee(employeeDetails.getEmpName(),date,datetime,datetime);

        when(employeeDaoMock.save(any())).thenReturn(emp);
        EmployeeDetails employee=employeeServiceImplementation.addEmployee(employeeDetails);
        assertEquals(emp.getEmpId(),employee.getEmpId());
        assertEquals(emp.getEmpName(),employee.getEmpName());
        assertEquals(emp.getEmpDoj(),employee.getEmpDate());
    }
}
