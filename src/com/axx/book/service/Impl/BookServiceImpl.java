package com.axx.book.service.Impl;

import com.axx.book.dao.BookDAO;
import com.axx.book.pojo.Book;
import com.axx.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDAO bookDAO = null;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }

    @Override
    public Book getBook(Integer id) {
        return bookDAO.getBook(id);
    }
}
