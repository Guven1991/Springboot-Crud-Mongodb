package com.example.springbootcrudexampledemo.error;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(String id){

        super("Author not found : " + id);
    }
}
