package com.uk.libraryManagementSystem.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Data
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;
}