package com.axx.book.dao.Impl;

import com.axx.book.dao.UserDAO;
import com.axx.book.pojo.User;
import com.axx.myssm.basedao.BaseDAO;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) {
        return super.load("select * from t_user where uname = ? and pwd = ?", uname, pwd);
    }

    @Override
    public void addUser(User user) {
        super.executeUpdate("insert into t_user values (0,?,?,?,0)", user.getUname(), user.getPwd(), user.getEmail());
    }

    @Override
    public User getUserByUname(String uname) {
        return load("select * from t_user where uname = ? " , uname);
    }


}
