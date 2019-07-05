package com.ydq.servlet;

import com.ydq.model.User;
import com.ydq.service.PaymentService;
import com.ydq.util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ModifyServlet")
public class ModifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");
        String modify_id = req.getParameter("modify_id");
        String modify_usage = req.getParameter("modify_usage");
        String modify_amount = req.getParameter("modify_amount");
        PaymentService paymentService = new PaymentService();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int result = paymentService.modifyPayment(user.getId(),Double.parseDouble(modify_amount),modify_usage,modify_id);
        boolean used = false;
        if (result > 0) {
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
