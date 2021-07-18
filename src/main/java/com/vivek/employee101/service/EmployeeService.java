package com.vivek.employee101.service;

import com.vivek.employee101.exceptions.EmployeeAlreadyExistsException;
import com.vivek.employee101.exceptions.EmployeeDoesNotExistsException;
import com.vivek.employee101.model.Employee;
import com.vivek.employee101.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//The @Component annotation marks a java class as a bean so the component-scanning mechanism of spring can pick it up and
// pull it into the application context.
// The @Service annotation is also a specialization of the component annotation
@Service
public class EmployeeService {
//    Autowiring feature of spring framework enables you to inject the object dependency
    @Autowired
    private final EmployeeRepository employeeRepository;
    //so we will get hold of our functions defined under JPARepository
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Service to GET all employees START
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    //Service to GET all employees END

    //Service to GET an employee by id START
    public Employee getEmployees(Long id) throws EmployeeDoesNotExistsException {
//        AOP for checking whether connected or not.
        return employeeRepository.findById(id)
           .orElseThrow(() ->new EmployeeDoesNotExistsException("Employee with id "+id+" was not found") );
    }
    //Service to GET an employee by id END

    //Service to ADD an employee START
    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        if(employeeRepository.findById(employee.getId()).isPresent()){
            System.out.println("In exception");
            throw new EmployeeAlreadyExistsException();
        }
        System.out.println("Not in exception");
        return employeeRepository.save(employee);
    }
    //Service to ADD an employee END

    //Service to UPDATE an employee START
    public Employee updateEmployee(Employee employee) throws EmployeeDoesNotExistsException {
        if(!employeeRepository.findById(employee.getId()).isPresent()){
            throw new EmployeeDoesNotExistsException();
        }
        return employeeRepository.save(employee);
    }
    //Service to UPDATE an employee END

    //Service to delete en employee START
    public Employee deleteEmployee(Long employeeId)throws EmployeeDoesNotExistsException {
        Employee employee = null;
        Optional optional = employeeRepository.findById(employeeId);
        if (!optional.isPresent()) {
            throw new EmployeeDoesNotExistsException();
        }
        employee = employeeRepository.findById(employeeId).get();
        employeeRepository.deleteById(employeeId);
        return employee;
    }
    //Service to delete en employee END
}
