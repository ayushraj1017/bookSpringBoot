package com.springboot.book.springbook.services;

import java.util.List;
import com.springboot.book.springbook.Dao.BookRepository;
import com.springboot.book.springbook.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // get all BookService
    public List<Book> getAllBooks() {
        List<Book> list = (List<Book>) this.bookRepository.findAll();
        return list;
    }

    // get single book

    public Book getBookById(int id) {
        Book book = null;
        try {
            book = this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // add books into list

    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    public void deleteBookById(int id) {
        bookRepository.deleteById(id);
    }

    public void updateBookById(int bookId, Book book) {
        book.setId(bookId);
        bookRepository.save(book);
    }
}
