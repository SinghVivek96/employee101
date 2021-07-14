package com.vivek.employee101.service;

import com.vivek.employee101.exceptions.UserNotFoundException;
import com.vivek.employee101.model.Employee;
import com.vivek.employee101.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Employee getEmployees(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(() ->new UserNotFoundException("Employee with id "+id+" was not found") );
    }
    //Service to GET an employee by id END

    //Service to ADD/UPDATE an employee START
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    //Service to add en employee END

    //Service to delete en employee START
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }
    //Service to delete en employee END
}
