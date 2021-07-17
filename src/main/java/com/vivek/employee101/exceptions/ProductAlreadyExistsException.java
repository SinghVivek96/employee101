package com.vivek.employee101.exceptions;

public class ProductAlreadyExistsException extends Exception{
    private String message;

    public ProductAlreadyExistsException(){

    }

    public ProductAlreadyExistsException(String message){
        super();
        this.message = message;
    }

}