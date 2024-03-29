package com.ydq.servlet;

import com.ydq.filter.listener.SessionCountLisener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sc = SessionCountLisener.getSessionCount();
        req.setAttribute("sc",sc);
        req.getRequestDispatcher("test.jsp").forward(req,resp);
    }
}
