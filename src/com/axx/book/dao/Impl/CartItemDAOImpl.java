package com.axx.book.dao.Impl;

import com.axx.book.dao.CartItemDAO;
import com.axx.book.pojo.CartItem;
import com.axx.book.pojo.User;
import com.axx.myssm.basedao.BaseDAO;

import java.util.List;

public class CartItemDAOImpl extends BaseDAO<CartItem> implements CartItemDAO {
    @Override
    public void addCartItem(CartItem cartItem) {
        super.executeUpdate("insert into t_cart_item values (0,?,?,?)", cartItem.getBook().getId(), cartItem.getBuyCount(), cartItem.getUserBean().getId());
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        super.executeUpdate("update t_cart_item set buyCount = ? where id = ?", cartItem.getBuyCount(), cartItem.getId());
    }

    @Override
    public List<CartItem> getCartItemList(User user) {
        return super.executeQuery("select * from t_cart_item where userBean = ?",user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) {
        super.executeUpdate("delete from t_cart_item where id = ?" , cartItem.getId());
    }


}
