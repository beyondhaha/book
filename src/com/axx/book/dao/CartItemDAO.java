package com.axx.book.dao;

import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.User;

import java.util.List;

public interface CartItemDAO {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    List<CartItem> getCartItemList(User user);

    void delCartItem(CartItem cartItem);
}
