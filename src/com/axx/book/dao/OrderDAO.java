package com.axx.book.dao;

import com.axx.book.pojo.OrderBean;
import com.axx.book.pojo.User;

import java.util.List;

public interface OrderDAO {
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);

    Integer getOrderTotalBookCount(OrderBean orderBean);
}
