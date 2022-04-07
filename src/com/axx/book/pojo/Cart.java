package com.axx.book.pojo;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class Cart {
    private Map<Integer, CartItem> cartItemMap;//购物车中购物车项的集合
    private Double totalMoney;//购物车商品总金额
    private Integer totalCount;//购物车项的总数
    private Integer bookTotalCount;//购物车中书籍总数量

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    //利用大数计算double类型数据
    public Double getTotalMoney() {
        totalMoney = 0.0;
        BigDecimal bigDecimalTotalMoney = new BigDecimal(0.0);
        if (cartItemMap != null && cartItemMap.size() > 0) {
            Set<Map.Entry<Integer, CartItem>> entrySet = cartItemMap.entrySet();

            for (Map.Entry<Integer, CartItem> entry : entrySet) {
                CartItem cartItem = entry.getValue();
                BigDecimal bookPrice = new BigDecimal(cartItem.getBook().getPrice() + "");
                BigDecimal buyCount = new BigDecimal(cartItem.getBuyCount() + "");
                bigDecimalTotalMoney = bigDecimalTotalMoney.add(bookPrice.multiply(buyCount));
            }
        }
        totalMoney= bigDecimalTotalMoney.doubleValue();
        return totalMoney;
    }


    public Integer getTotalCount() {
        totalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            totalCount = cartItemMap.size();
        }
        return totalCount;
    }

    public Integer getBookTotalCount() {
        bookTotalCount = 0;
        if (cartItemMap != null && cartItemMap.size() > 0) {
            for (CartItem cartItem : cartItemMap.values()) {
                bookTotalCount += cartItem.getBuyCount();
            }
        }
        return bookTotalCount;
    }
}
