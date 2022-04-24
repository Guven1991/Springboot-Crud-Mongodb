package com.example.springbootcrudexampledemo.repository;

import com.example.springbootcrudexampledemo.entity.Book;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> getBooksByAuthor_Id(String Id);
}
