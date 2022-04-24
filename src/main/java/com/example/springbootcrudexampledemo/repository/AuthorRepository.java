package com.example.springbootcrudexampledemo.repository;

import com.example.springbootcrudexampledemo.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {

}
