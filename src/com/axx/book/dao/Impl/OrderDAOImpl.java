package com.axx.book.dao.Impl;

import com.axx.book.dao.OrderDAO;
import com.axx.book.pojo.OrderBean;
import com.axx.book.pojo.User;
import com.axx.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        //执行insert前，orderBean中id为null，执行后返回自增的id值，将其设置到orderBean中
        int orderBeanId = super.executeUpdate("insert into t_order values (0,?,?,?,?,?)", orderBean.getOrderNo(), orderBean.getOrderDate(), orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return super.executeQuery("select * from t_order where orderUser = ?", user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "SELECT SUM(t3.buyCount) AS totalBookCount , t3.orderBean FROM " +
                "(" +
                "SELECT t1.id , t2.buyCount , t2.orderBean FROM t_order t1 INNER JOIN t_order_item t2 " +
                "ON t1.id = t2.orderBean WHERE t1.orderUser = ? " +
                ") t3 WHERE t3.orderBean = ? GROUP BY t3.orderBean";
        return ((BigDecimal)executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();
    }
}
