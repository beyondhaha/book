package com.axx.book.service.Impl;

import com.axx.book.dao.CartItemDAO;
import com.axx.book.pojo.Book;
import com.axx.book.pojo.Cart;
import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.User;
import com.axx.book.service.BookService;
import com.axx.book.service.CartItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO = null;
    private BookService bookService = null;

    @Override
    public void addCartItem(CartItem cartItem) {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    //1、如果当前用户的购物车中已经存在这个图书了，那么将购物车中这本图书的数量+1
    //2、否则，在我的购物车中新增一个这本图书的CartItem，数量为1
    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) {
        //检查购物车是否为空
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();

            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }

            //检查当前购物车中是否存在该购物项的书籍
            //有则修改购物车中该书数量+1，没有则新增购物项
            Integer bookId = cartItem.getBook().getId();
            if (cartItemMap.containsKey(bookId)) {
                CartItem curCartItem = cartItemMap.get(bookId);
                curCartItem.setBuyCount(curCartItem.getBuyCount() + 1);
                updateCartItem(curCartItem);
            } else {
                addCartItem(cartItem);
            }
        } else {
            addCartItem(cartItem);
        }
    }

    //查询用户购物车信息
    @Override
    public Cart getCart(User user) {
        List<CartItem> cartItemList = getCartItemList(user);
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (CartItem cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        cart.getBookTotalCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        return cart;
    }

    //获取指定用户的购物车项列表(在内部查询时，会将其中的book的详细信息的设置进去)
    @Override
    public List<CartItem> getCartItemList(User user) {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for (CartItem cartItem : cartItemList) {
            Integer bookId = cartItem.getBook().getId();
            Book book = bookService.getBook(bookId);
            cartItem.setBook(book);
            //此处需要调用getXj()，目的是执行getXj()内部的代码，让book的price乘以buyCount，从而计算出xj这个属性的值
            cartItem.getXj();
        }
        return cartItemList;
    }


}
