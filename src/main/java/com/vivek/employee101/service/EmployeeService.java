package com.vivek.employee101.service;

import com.vivek.employee101.exceptions.ProductAlreadyExistsException;
import com.vivek.employee101.exceptions.ProductDoesNotExistsException;
import com.vivek.employee101.model.Employee;
import com.vivek.employee101.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //Service to GET all employees START
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }
    //Service to GET all employees END

    //Service to GET an employee by id START
    public Employee getEmployees(Long id) throws ProductDoesNotExistsException{
        return employeeRepository.findById(id)
           .orElseThrow(() ->new ProductDoesNotExistsException("Employee with id "+id+" was not found") );
    }
    //Service to GET an employee by id END

    //Service to ADD an employee START
    public Employee addEmployee(Employee employee) throws ProductAlreadyExistsException {
        if(employeeRepository.findById(employee.getId()).isPresent()){
            System.out.println("In exception");
            throw new ProductAlreadyExistsException();
        }
        System.out.println("Not in exception");
        return employeeRepository.save(employee);
    }
    //Service to ADD an employee END

    //Service to UPDATE an employee START
    public Employee updateEmployee(Employee employee) throws ProductDoesNotExistsException {
        if(!employeeRepository.findById(employee.getId()).isPresent()){
            throw new ProductDoesNotExistsException();
        }
        return employeeRepository.save(employee);
    }
    //Service to UPDATE an employee END

    //Service to delete en employee START
    public Employee deleteEmployee(Long employeeId)throws ProductDoesNotExistsException{
        Employee employee = null;
        Optional optional = employeeRepository.findById(employeeId);
        if (!optional.isPresent()) {
            throw new ProductDoesNotExistsException();
        }
        employee = employeeRepository.findById(employeeId).get();
        employeeRepository.deleteById(employeeId);
        return employee;
    }
    //Service to delete en employee END
}
