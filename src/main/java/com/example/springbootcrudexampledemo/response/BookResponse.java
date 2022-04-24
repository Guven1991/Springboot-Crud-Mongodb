package com.example.springbootcrudexampledemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookResponse {

    private String id;

    private String name;

    private AuthorResponse author;

    private Double price;
}
