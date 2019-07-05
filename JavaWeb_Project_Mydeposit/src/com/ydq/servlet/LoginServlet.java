package com.ydq.servlet;

import com.ydq.model.User;
import com.ydq.service.PaymentService;
import com.ydq.service.UserService;
import com.ydq.util.PageUtil;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("**doPost**");
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");

        String name = req.getParameter("user.username");
        String pwd = req.getParameter("user.password");

        //将用户名、保存入cookie中
        Cookie cookie = new Cookie("userInfo",name + "-" + pwd);
        resp.addCookie(cookie);

        UserService userService = new UserService();
        User user = userService.login(name,pwd);
        String tips = "";
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            PaymentService paymentService = new PaymentService();
            PageUtil page = paymentService.selectPaymentConditionPage(user.getId(),null,null,null,null,null);
            req.removeAttribute("tips");
            req.setAttribute("page",page);
            req.getRequestDispatcher("paymentList.jsp").forward(req, resp);
        } else {
            tips = "用户不存在，登录失败！";
            req.setAttribute("tips", tips);
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
