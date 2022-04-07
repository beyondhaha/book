package com.axx.book.dao.Impl;

import com.axx.book.dao.BookDAO;
import com.axx.book.pojo.Book;
import com.axx.myssm.basedao.BaseDAO;

import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        List<Book> bookList = super.executeQuery("select * from t_book where bookStatus = 0");
        return bookList;
    }

    @Override
    public Book getBook(Integer id) {
        return super.load("select * from t_book where id = ?", id);
    }
}
