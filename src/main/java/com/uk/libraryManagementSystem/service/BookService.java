package com.uk.libraryManagementSystem.service;

import com.uk.libraryManagementSystem.entity.Book;
import com.uk.libraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        book.setAvailable(true);
        return bookRepository.save(book);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }

    public Optional<Book> borrowBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        // Check if the book exists and is currently available
        if (bookOptional.isPresent() && bookOptional.get().isAvailable()) {
            Book book = bookOptional.get();
            book.setAvailable(false);
            bookRepository.save(book);
            return Optional.of(book);
        }
                return Optional.empty();
    }

    public Optional<Book> returnBook(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
               if (bookOptional.isPresent() && !bookOptional.get().isAvailable()) {
            Book book = bookOptional.get();
            book.setAvailable(true);
            bookRepository.save(book);
            return Optional.of(book);
        }
               return Optional.empty();
    }
}