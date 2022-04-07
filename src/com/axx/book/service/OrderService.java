package com.axx.book.service;

import com.axx.book.pojo.OrderBean;
import com.axx.book.pojo.User;

import java.util.List;

public interface OrderService {
    void addOrderBean(OrderBean orderBean);

    List<OrderBean> getOrderList(User user);
}
