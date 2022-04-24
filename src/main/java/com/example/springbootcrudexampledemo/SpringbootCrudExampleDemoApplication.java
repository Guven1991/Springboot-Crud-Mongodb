package com.example.springbootcrudexampledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SpringbootCrudExampleDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCrudExampleDemoApplication.class, args);
    }
}
