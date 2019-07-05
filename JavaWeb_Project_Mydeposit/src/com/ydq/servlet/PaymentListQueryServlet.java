package com.ydq.servlet;
import com.ydq.model.User;
import com.ydq.service.PaymentService;
import com.ydq.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PaymentListQueryServlet")
public class PaymentListQueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf8");
        resp.setContentType("text/html;charset=UTF-8");
        String pageNumber = req.getParameter("pageNumber");
        String startDate = req.getParameter("startDate");
        String endDate = req.getParameter("endDate");
        String minAmount = req.getParameter("minAmount");
        String maxAmount = req.getParameter("maxAmount");

        PaymentService paymentService = new PaymentService();
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        PageUtil page = paymentService.selectPaymentConditionPage(user.getId(),pageNumber, startDate, endDate, minAmount, maxAmount);
        req.setAttribute("page", page);
        req.getRequestDispatcher("paymentList.jsp").forward(req, resp);
    }
}
