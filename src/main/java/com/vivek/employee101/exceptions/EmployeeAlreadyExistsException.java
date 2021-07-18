package com.vivek.employee101.exceptions;

public class EmployeeAlreadyExistsException extends Exception{
    private String message;

    public EmployeeAlreadyExistsException(){

    }

    public EmployeeAlreadyExistsException(String message){
        super();
        this.message = message;
    }

}