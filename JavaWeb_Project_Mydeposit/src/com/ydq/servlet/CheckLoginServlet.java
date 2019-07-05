package com.ydq.servlet;

import com.ydq.util.JDBCUtilCopy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("name");
        String sql = "select * from user where username = ?";
        Object[] params = {name};
        List<Map<String, Object>> list = JDBCUtilCopy.select(sql, params);
        boolean used = false;
        if (list != null && list.size() != 0) {
            used = true;
        } else {
            used = false;
        }
        PrintWriter out = resp.getWriter();
        out.print(used);
        out.flush();
        out.close();
    }
}
