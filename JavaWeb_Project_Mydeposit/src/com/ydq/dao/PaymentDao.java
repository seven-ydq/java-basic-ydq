package com.ydq.dao;

import com.ydq.model.Payment;
import com.ydq.util.PageUtil;

import java.util.List;
import java.util.Map;


public interface PaymentDao {
    int insertPayment(String userId, Payment payment);

    int modifyPayment(String userId, Payment payment);

    int deletePayment(Payment payment);

    List<Map<String, Object>> selectPaymentConditionPage(String userId,PageUtil page, String startDate, String endDate, String minAmount, String maxAmount);

    int selectPaymentPageCount(String userId,PageUtil page, String startDate, String endDate, String minAmount, String maxAmount);


}
