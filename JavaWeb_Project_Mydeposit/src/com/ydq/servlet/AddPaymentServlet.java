package com.ydq.servlet;

import com.ydq.model.User;
import com.ydq.service.PaymentService;
import com.ydq.util.JDBCUtilCopy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@WebServlet("/AddPaymentServlet")
public class AddPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");
        String p_amount = req.getParameter("pay_amount");
        String p_usage = req.getParameter("pay_usage");
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");

        PaymentService paymentService = new PaymentService();
        paymentService.insertPayment(user.getId(),Double.parseDouble(p_amount),p_usage);

        String sql_pay = "select * from payment where DATE_FORMAT(PAY_DATE , '%Y%m') = DATE_FORMAT(CURDATE( ) , '%Y%m') and userid='" + user.getId() + "'";
        List<Map<String, Object>> paymentList = JDBCUtilCopy.select(sql_pay);
        req.setAttribute("paymentList",paymentList);
        req.getRequestDispatcher("paymentList.jsp").forward(req, resp);

    }
}
