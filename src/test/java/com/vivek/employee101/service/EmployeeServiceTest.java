package com.vivek.employee101.service;

import com.vivek.employee101.exceptions.EmployeeAlreadyExistsException;
import com.vivek.employee101.exceptions.EmployeeDoesNotExistsException;
import com.vivek.employee101.model.Employee;
import com.vivek.employee101.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    @InjectMocks
    private EmployeeService employeeService;
    private Employee employee1;
    private Employee employee2;
    List<Employee> employeeList;


    private EmployeeService underTest;

    @BeforeEach
    public void setUp() {
        long     id1      = 1040222;
        String   name1    = "Vivek Singh";
        float    salary1  = 1234567;
        String   address1 = "636/9, Ashutosh Vihar, Sec 11, Indira Nagar, Lucknow";

        long     id2      = 1040223;
        String   name2    = "Aman Singh";
        float    salary2  = 12343269;
        String   address2 = "14/628, Indira Nagar, Lucknow";

        employeeList = new ArrayList<>();
        employee1 = new Employee(id1, name1,salary1,address1);
        employee2 = new Employee(id2, name2,salary2,address2);
        employeeList.add(employee1);
        employeeList.add(employee2);
    }

    @AfterEach
    public void tearDown() {
        employee1 = employee2 = null;
        employeeList = null;
    }

    @Test
    void canGetAllEmployees() {
        //stubbing mock to return specific data
        when(employeeRepository.findAll()).thenReturn(employeeList);
        List<Employee> capturedEmployeeList = employeeService.getEmployees();
        System.out.println(capturedEmployeeList);
        assertEquals(capturedEmployeeList,employeeList);
        verify(employeeRepository,times(1)).save(employee1);
        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    void canGetEmployeeByIdWhenEmployeePresent() throws EmployeeDoesNotExistsException {
        Mockito.when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.ofNullable(employee1));
        Employee capturedEmployee = employeeService.getEmployees(employee1.getId());
        assertThat(capturedEmployee).isEqualTo(employee1);
    }


    @Test
    void canAddEmployeeIfNotExists() throws EmployeeAlreadyExistsException {
        //stubbing
        when(employeeRepository.save(any())).thenReturn(employee1);
        Employee capturedEmployee = employeeService.addEmployee(employee1);
        System.out.println(capturedEmployee);
        assertEquals(capturedEmployee,employee1);
        verify(employeeRepository,times(1)).save(any());
    }

    @Test
    void canAddEmployeeIfExists() throws EmployeeAlreadyExistsException {
        //stubbing
        when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.ofNullable(employee1));
        assertThrows(EmployeeAlreadyExistsException.class, () -> employeeService.addEmployee(employee1));
        verify(employeeRepository,times(1)).findById(employee1.getId());
    }

    @Test
    void canUpdateEmployeeIfExists() throws EmployeeDoesNotExistsException {
        //stubbing
        when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.ofNullable(employee1));
        when(employeeRepository.save(any())).thenReturn(employee1);
        Employee capturedEmployee = employeeService.updateEmployee(employee1);
        assertEquals(capturedEmployee,employee1);
        verify(employeeRepository,times(1)).save(any());
    }

    @Test
    void canUpdateEmployeeIfDoesNotExists() throws EmployeeDoesNotExistsException {
        assertThrows(EmployeeDoesNotExistsException.class, () -> employeeService.updateEmployee(employee1));
        verify(employeeRepository,times(1)).findById(employee1.getId());
    }

    @Test
    void canDeleteEmployeeIfExists() throws EmployeeDoesNotExistsException {
        //stubbing
        when(employeeRepository.findById(employee1.getId())).thenReturn(Optional.ofNullable(employee1));
        doNothing().when(employeeRepository).deleteById(employee1.getId());
        //then
        employeeService.deleteEmployee(employee1.getId());
        //stubbing
        when(employeeRepository.findById(employee1.getId())).thenReturn(null);
        //then
        assertEquals(employeeRepository.findById(employee1.getId()),null);
        verify(employeeRepository,times(3)).findById(employee1.getId());
        verify(employeeRepository,times(1)).deleteById(employee1.getId());
    }

    @Test
    void canNotDeleteEmployeeIfDoesNotExists() throws EmployeeDoesNotExistsException {
        assertThrows(EmployeeDoesNotExistsException.class, () -> employeeService.deleteEmployee(employee1.getId()));
        verify(employeeRepository,times(1)).findById(employee1.getId());
    }


}
