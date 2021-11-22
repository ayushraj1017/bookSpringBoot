package com.springboot.book.springbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.book.springbook.services.BookService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import com.springboot.book.springbook.entities.Book;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // get all books
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> lists = this.bookService.getAllBooks();
        if (lists.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(lists));
    }

    // get books by ID
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        Book singleBook = this.bookService.getBookById(id);
        if (singleBook == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(singleBook));
    }

    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") int id) {
        try {
            this.bookService.deleteBookById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable("id") int bookId, @RequestBody Book book) {
        try {
            this.bookService.updateBookById(bookId, book);
            return ResponseEntity.ok().body(book);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}