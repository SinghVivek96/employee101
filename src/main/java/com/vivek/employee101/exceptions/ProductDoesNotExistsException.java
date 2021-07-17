package com.vivek.employee101.exceptions;

public class ProductDoesNotExistsException extends Exception{
    private String message;

    public ProductDoesNotExistsException(){

    }

    public ProductDoesNotExistsException(String message){
        super();
        this.message = message;
    }
}
