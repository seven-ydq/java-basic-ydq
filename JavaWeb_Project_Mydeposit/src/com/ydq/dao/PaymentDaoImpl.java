package com.ydq.dao;

import com.ydq.model.Payment;
import com.ydq.util.JDBCUtil;
import com.ydq.util.JDBCUtilCopy;
import com.ydq.util.PageUtil;

import java.util.List;
import java.util.Map;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public int insertPayment(String userId, Payment payment) {
        String sql_insertPayment = "INSERT INTO PAYMENT (id,pay_amount,pay_usage,pay_date,userid) VALUES (?,?,?,?,?)";
        Object[] params = {payment.getId(),payment.getPay_amount(),payment.getPay_usage(),payment.getPay_date(),userId};
        return JDBCUtilCopy.update(sql_insertPayment,params);
    }

    @Override
    public int modifyPayment(String userId, Payment payment) {
        String sql_modify = "UPDATE payment SET pay_amount = ?,pay_usage = ? WHERE id = ?";
        Object[] params = {payment.getPay_amount(),payment.getPay_usage(),payment.getId()};
        return JDBCUtilCopy.update(sql_modify,params);
    }

    @Override
    public int deletePayment(Payment payment) {
        String sql = "delete from payment where id = ?";
        Object[] params = {payment.getId()};
        return JDBCUtilCopy.update(sql,params);
    }

    @Override
    public List<Map<String, Object>> selectPaymentConditionPage(String userId, PageUtil page, String startDate, String endDate, String minAmount, String maxAmount) {
        String query_sql = "SELECT * FROM payment WHERE userid = '" + userId + "'";
        if (startDate != null && !startDate.equals("")) {
            query_sql += " and pay_date >= '" + startDate + "'";
        }
        if (endDate != null && !endDate.equals("")) {
            query_sql += " and pay_date <= '" + endDate + "'";

        }
        if (minAmount != null && !minAmount.equals("")) {
            query_sql += " and pay_amount >= '" + minAmount + "'";

        }
        if (maxAmount != null && !maxAmount.equals("")) {
            query_sql += " and pay_amount <= '" + maxAmount + "'";
        }
        query_sql += " limit " + (page.getPageNumber()-1) * 5 + ", " + page.getPerPageNumber();
        return JDBCUtilCopy.select(query_sql);
    }

    @Override
    public int selectPaymentPageCount(String userId,PageUtil page, String startDate, String endDate, String minAmount, String maxAmount) {
        String query_sql = "SELECT count(*) as totalDataNumber FROM payment WHERE userid = '" + userId + "'";
        if (startDate != null && !startDate.equals("")) {
            query_sql += " and pay_date >= '" + startDate + "'";
        }
        if (endDate != null && !endDate.equals("")) {
            query_sql += " and pay_date <= '" + endDate + "'";

        }
        if (minAmount != null && !minAmount.equals("")) {
            query_sql += " and pay_amount >= '" + minAmount + "'";

        }
        if (maxAmount != null && !maxAmount.equals("")) {
            query_sql += " and pay_amount <= '" + maxAmount + "'";
        }
        return new Integer(String.valueOf(JDBCUtilCopy.select(query_sql).get(0).get("totalDataNumber")));
    }
}
