package com.axx.book.service;

import com.axx.book.pojo.Cart;
import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);

    void updateCartItem(CartItem cartItem);

    void addOrUpdateCartItem(CartItem cartItem, Cart cart);

    Cart getCart(User user);

    List<CartItem> getCartItemList(User user);
}
