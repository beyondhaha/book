package com.axx.book.service;

import com.axx.book.pojo.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookList();
    Book getBook(Integer id);
}
