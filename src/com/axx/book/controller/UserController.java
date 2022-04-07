package com.axx.book.controller;

import com.axx.book.pojo.Cart;
import com.axx.book.pojo.User;
import com.axx.book.service.CartItemService;
import com.axx.book.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserController {
    private UserService userService = null;
    private CartItemService cartItemService = null;

    String login(String uname, String pwd, HttpSession session) {
        User user = userService.login(uname, pwd);
        if (user != null) {
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do";
        }
        return "user/login";
    }

    String regist(String verifyCode, String uname, String pwd, String email, HttpSession session, HttpServletResponse resp) throws IOException {
        Object kaptcha = session.getAttribute("KAPTCHA_SESSION_KEY");

        //验证码未生成或输入验证码错误
        if (kaptcha == null || !kaptcha.equals(verifyCode)) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script language='javascript'>alert('验证码不正确！');</script>");
            return "user/regist";
        } else if (verifyCode.equals(kaptcha)) {
            userService.regist(new User(uname, pwd, email));
            return "user/login";
        }

        return "user/login";
    }
}
