package com.ydq.servlet;

import com.ydq.service.UserService;
import com.ydq.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf8");
        String name = req.getParameter("uname");
        String pswd = req.getParameter("psw");

        UserService userService = new UserService();

        String tips = "";
        if (userService.select(name))  {
            tips = "用户已存在，请登录！";
        } else {
            if (userService.addUser(name,pswd)) {
                tips = "注册成功请登录";
            } else {
                tips = "注册失败！请重新填写信息";
            }
        }
        req.setAttribute("tips", tips);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
