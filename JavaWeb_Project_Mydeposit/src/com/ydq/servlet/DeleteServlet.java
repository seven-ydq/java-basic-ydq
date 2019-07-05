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

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");

        String delete_spend  = req.getParameter("pid");
        PaymentService paymentService = new PaymentService();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        boolean used = false;
        if (paymentService.deletePayment(delete_spend) != 0){
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
