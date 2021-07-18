package com.vivek.employee101.model;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity 
public class Employee {


    @ApiModelProperty(notes = "Id of an Employee", name ="id", required = true, value = "1040222")
    @Id 
    private Long   id;

    @ApiModelProperty(notes = "Name of an Employee", name ="name", required = true, value = "Vivek Singh")
    private String name;

    @ApiModelProperty(notes = "Salary of an Employee", name ="salary", required = true, value = "1234567")
    private Float  salary;

    @ApiModelProperty(notes = "Address of an Employee", name ="address", required = true, value = "1040222")
    private String address;

    public Employee() {
    }

    public Employee(Long id, String name, Float salary, String address) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                '}';
    }
}
