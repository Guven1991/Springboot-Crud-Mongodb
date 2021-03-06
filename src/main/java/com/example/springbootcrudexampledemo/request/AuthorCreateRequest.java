package com.example.springbootcrudexampledemo.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorCreateRequest {

    private String id;

    private String name;

    private String lastname;

}
