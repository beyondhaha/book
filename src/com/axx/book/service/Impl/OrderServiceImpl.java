package com.axx.book.service.Impl;

import com.axx.book.dao.CartItemDAO;
import com.axx.book.dao.OrderDAO;
import com.axx.book.dao.OrderItemDAO;
import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.OrderBean;
import com.axx.book.pojo.OrderItem;
import com.axx.book.pojo.User;
import com.axx.book.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private OrderDAO orderDAO = null;
    private OrderItemDAO orderItemDAO = null;
    private CartItemDAO cartItemDAO = null;

    @Override
    public void addOrderBean(OrderBean orderBean) {
        //1、向订单表中增加一条记录
        orderDAO.addOrderBean(orderBean);

        //2、向订单详情表中添加7条记录
        //orderBean中的orderItemList为空，这里将用户的购物车项信息转换成对应的订单项信息
        User orderUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemMap = orderUser.getCart().getCartItemMap();

        for (CartItem cartItem : cartItemMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        //3、删除购物车项表中对应的7条记录
        for (CartItem cartItem : cartItemMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        List<OrderBean> orderList = orderDAO.getOrderList(user);

        for (OrderBean orderBean : orderList) {
            Integer orderTotalBookCount = orderDAO.getOrderTotalBookCount(orderBean);
            orderBean.setTotalBookCount(orderTotalBookCount);
        }

        return orderList;
    }
}
