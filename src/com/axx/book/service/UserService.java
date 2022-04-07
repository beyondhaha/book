package com.axx.book.service;

import com.axx.book.pojo.User;

public interface UserService {
    //登录
    User login(String uname, String pwd);

    //添加用户
    void regist(User user);

    //通过用户名获取用户
    User getUserByUname(String uname);
}
