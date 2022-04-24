package com.example.springbootcrudexampledemo.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorResponse {

    private String id;

    private String name;

    private String lastname;

//    private List<BookResponse> bookList;


}
