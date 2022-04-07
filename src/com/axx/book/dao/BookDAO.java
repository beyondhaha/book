package com.axx.book.dao;

import com.axx.book.pojo.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getBookList();
    Book getBook(Integer id);
}
