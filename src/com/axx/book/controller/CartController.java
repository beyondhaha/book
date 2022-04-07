package com.axx.book.controller;

import com.axx.book.pojo.Book;
import com.axx.book.pojo.Cart;
import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.User;
import com.axx.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService = null;

    public String index(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);

        return "cart/cart";
    }

    public String addCart(Integer bookId, HttpSession session) {
        //将指定的图书添加到当前用户的购物车中
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());

        //重新查询购物车中信息
        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId, Integer buyCount) {
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));

        return "";
    }

    public String cartInfo(HttpSession session) {
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:" + cartJsonStr;
    }
}
