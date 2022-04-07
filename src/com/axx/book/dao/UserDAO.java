package com.axx.book.dao;

import com.axx.book.pojo.User;

public interface UserDAO {
    User getUser(String uname, String pwd);

    void addUser(User user);

    User getUserByUname(String uname);
}
