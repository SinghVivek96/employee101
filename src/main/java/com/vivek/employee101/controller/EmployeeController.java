package com.vivek.employee101.controller;

import com.vivek.employee101.model.Employee;
import com.vivek.employee101.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ApiOperation(value = "/employee", tags = "Employee Controller")
@RestController
@RequestMapping("/")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//   Getting All the employee START
    @ApiOperation(value = "Fetch all the employees", response = Iterable.class)
    @GetMapping("/employee")
    @ApiResponses (value = {
            @ApiResponse(code = 200,  message = "SUCCESS",        response = Employee.class),
            @ApiResponse(code = 404,  message = "NOT FOUND")})

    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getEmployees(), HttpStatus.OK);
    }
//   Getting All the employee END

//   Getting employee by employee id START
    @ApiOperation(value = "Fetch an employee based on id", response = Employee.class)
    @ApiResponses (value = {
            @ApiResponse(code = 200,  message = "SUCCESS",        response = Employee.class),
            @ApiResponse(code = 404,  message = "NOT FOUND"),
            @ApiResponse(code = 500,  message = "USER NOT FOUND")})

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployees(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.getEmployees(id), HttpStatus.OK);
    }
//   Getting employee by employee id END

//   Add an employee START
    @ApiOperation(value = "Add an employee", response = Employee.class)
    @ApiResponses (value = {
            @ApiResponse(code = 201,  message = "EMPLOYEE SUCCESSFULLY CREATED",        response = Employee.class),
            @ApiResponse(code = 404,  message = "NOT FOUND")})

    @PostMapping("/employee/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee),HttpStatus.CREATED);
    }
//   Add an employee END

//   Update an employee START
    @ApiOperation(value = "Update an employee", response = Employee.class)
    @ApiResponses (value = {
            @ApiResponse(code = 200,  message = "EMPLOYEE SUCCESSFULLY UPDATED",        response = Employee.class),
            @ApiResponse(code = 404,  message = "NOT FOUND")})

    @PatchMapping("/employee/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee),HttpStatus.OK);
    }
//   Update an employee END

//   Delete an employee START
    @ApiOperation(value = "Delete an employee based on id", response = HttpStatus.class)
    @ApiResponses (value = {
            @ApiResponse(code = 200,  message = "EMPLOYEE SUCCESSFULLY DELETED",        response = Employee.class),
            @ApiResponse(code = 404,  message = "NOT FOUND")})

    @DeleteMapping("/employee/delete/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//   Delete an employee END
}
