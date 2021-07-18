package com.vivek.employee101.exceptions;

public class EmployeeDoesNotExistsException extends Exception{
    private String message;

    public EmployeeDoesNotExistsException(){

    }

    public EmployeeDoesNotExistsException(String message){
        super();
        this.message = message;
    }
}
