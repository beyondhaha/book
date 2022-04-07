package com.axx.book.service.Impl;

import com.axx.book.dao.UserDAO;
import com.axx.book.pojo.User;
import com.axx.book.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = null;

    @Override
    public User login(String uname, String pwd) {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void regist(User user) {
        userDAO.addUser(user);
    }

    @Override
    public User getUserByUname(String uname) {
        return userDAO.getUserByUname(uname);
    }
}
