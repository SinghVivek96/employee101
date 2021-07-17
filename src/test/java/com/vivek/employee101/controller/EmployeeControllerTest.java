package com.vivek.employee101.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivek.employee101.model.Employee;
import com.vivek.employee101.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;
    private Employee employee1;
    private Employee employee2;
    private List<Employee> employeeList;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup(){
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

        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @AfterEach
    void tearDown() {
        employee1 = employee2 = null;
    }

    @Test
    void canGetAllTheEmployees() throws Exception{
        when(employeeService.getEmployees()).thenReturn(employeeList);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee").
                contentType(MediaType.APPLICATION_JSON).
                content(String.valueOf(employee1))).
                andDo(MockMvcResultHandlers.print());
        verify(employeeService).getEmployees();
        verify(employeeService,times(1)).getEmployees();
    }

    @Test
    void canGetEmployeeWithEmployeeIdIfPresent() throws Exception{
        when(employeeService.getEmployees(employee1.getId())).thenReturn(employee1);
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1040222")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addEmployee() throws Exception {
        when(employeeService.addEmployee(any())).thenReturn(employee1);
        mockMvc.perform(MockMvcRequestBuilders.post("/employee/add").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(employee1))).
                andExpect(status().isCreated());
        verify(employeeService,times(1)).addEmployee(any());
    }

    @Test
    void updateEmployee() throws Exception {
        when(employeeService.updateEmployee(any())).thenReturn(employee1);
        mockMvc.perform(MockMvcRequestBuilders.patch("/employee/update").
                contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(employee1))).
                andExpect(status().isOk());
        verify(employeeService,times(1)).updateEmployee(any());
    }

    @Test
    void deleteEmployee() throws Exception {
        when(employeeService.deleteEmployee(employee1.getId())).thenReturn(employee1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/delete/1040222")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(employee1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(employeeService,times(1)).deleteEmployee(employee1.getId());
    }

    public static String asJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}