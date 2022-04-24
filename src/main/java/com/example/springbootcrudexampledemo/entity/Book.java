package com.example.springbootcrudexampledemo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private String id;

    private String name;

//    @ManyToOne
    private Author author;

    private Double price;


}

