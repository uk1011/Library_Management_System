package com.uk.libraryManagementSystem.controller;

import com.uk.libraryManagementSystem.entity.Book;
import com.uk.libraryManagementSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> availableBooks = bookService.getAvailableBooks();
        return ResponseEntity.ok(availableBooks);
    }

    @PatchMapping("/{id}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable String id) {
        return bookService.borrowBook(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PatchMapping("/{id}/return")
    public ResponseEntity<Book> returnBook(@PathVariable String id) {
        return bookService.returnBook(id)
                .map(book -> ResponseEntity.ok(book))
                .orElse(ResponseEntity.badRequest().build());
    }
}