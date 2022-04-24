package com.example.springbootcrudexampledemo.error;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(String id){

        super("Book id not found : " + id);
    }
}
