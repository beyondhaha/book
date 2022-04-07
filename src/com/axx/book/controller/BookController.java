package com.axx.book.controller;

import com.axx.book.pojo.Book;
import com.axx.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService = null;

    public String index(HttpSession session) {
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList", bookList);
        return "index";
    }
}
