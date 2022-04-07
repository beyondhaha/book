package com.axx.book.controller;

import com.axx.book.pojo.OrderBean;
import com.axx.book.pojo.User;
import com.axx.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService = null;

    //结账
    public String checkOut(HttpSession session) {
        OrderBean orderBean = new OrderBean();
        User currUser = (User) session.getAttribute("currUser");

        String dateStr = "";
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(now);
        //按 - 空格 : 三种符号对日期进行切割
        String[] split = nowStr.split("-| |:");
        for (String s : split) {
            dateStr = dateStr + s;
        }

        orderBean.setOrderNo(UUID.randomUUID().toString() + "_" + dateStr);
        orderBean.setOrderDate(now);
        orderBean.setOrderUser(currUser);
        orderBean.setOrderMoney(currUser.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);

        return "index";
    }

    //查询用户订单信息
    public String getOrderList(HttpSession session) {
        User currUser = (User) session.getAttribute("currUser");

        List<OrderBean> orderList = orderService.getOrderList(currUser);
        currUser.setOrderList(orderList);
        session.setAttribute("currUser", currUser);

        return "order/order";
    }
}
