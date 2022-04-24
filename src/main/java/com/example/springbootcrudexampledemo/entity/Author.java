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
public class Author {

    @Id
    private String id;

    private String name;

    private String lastname;

//    @JsonManagedReference
//    @OneToMany(mappedBy ="author", cascade = CascadeType.REMOVE)        // mappedBy="user" Hoax tablosundakı user sütünu bizim forenkey imizdir demek
//    private List<Book> books;

}
