package com.springboot.book.springbook.Dao;

import com.springboot.book.springbook.entities.Book;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    public Book findById(int id);

}
