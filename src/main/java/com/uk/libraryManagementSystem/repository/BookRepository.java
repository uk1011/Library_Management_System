package com.uk.libraryManagementSystem.repository;

import com.uk.libraryManagementSystem.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findByAvailable(boolean available);
}