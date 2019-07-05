package com.ydq.service;

import com.ydq.dao.PaymentDao;
import com.ydq.dao.PaymentDaoImpl;
import com.ydq.model.Payment;
import com.ydq.util.PageUtil;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PaymentService {
    /**
     * 出账操作--查询--根据用户点击的当前页查询
     * @param userId
     * @param pageNumber
     * @param minDate
     * @param maxDate
     * @param minAmount
     * @param maxAmount
     * @return page
     */

    public PageUtil selectPaymentConditionPage(String userId,String pageNumber, String minDate,String maxDate,String minAmount,String maxAmount){
        PaymentDao paymentDao = new PaymentDaoImpl();
        PageUtil page = new PageUtil();
        if (pageNumber == null || pageNumber.equals("")) {
            pageNumber = "1";
        }
        page.setPageNumber(new Integer(pageNumber));

        int perPageNumber = page.getPerPageNumber();
        int totalDataNumber = paymentDao.selectPaymentPageCount(userId,page,minDate,maxDate,minAmount,maxAmount);
        page.setTotalDataNumber(totalDataNumber);

        int totalPageNumber = 0;
        if (totalDataNumber % perPageNumber == 0) {
            totalPageNumber = totalDataNumber / perPageNumber;
        } else {
            totalPageNumber = totalDataNumber / perPageNumber + 1;
        }
        page.setTotalPageNumber(totalPageNumber);

        List<Map<String, Object>> paymentList = paymentDao.selectPaymentConditionPage(userId,page,minDate,maxDate,minAmount,maxAmount);
        page.setPageDataList(paymentList);
        return page;
    }

    /**
     * 出账操作--添加--根据金额、用途添加一条出账数据
     * @param pay_amount
     * @param pay_usage
     * @return int
     */
    public int insertPayment(String userId,double pay_amount, String pay_usage){
        Payment payment = new Payment();
        payment.setId(UUID.randomUUID().toString());
        payment.setPay_amount(pay_amount);
        payment.setPay_usage(pay_usage);
        payment.setPay_date(new Date());
        PaymentDao paymentDao = new PaymentDaoImpl();
        return paymentDao.insertPayment(userId,payment);
    }

    /**
     * 出账--修改--修改选中数据的modify_amount、modify_usage
     * @param modify_amount
     * @param modify_usage
     * @param modify_id
     * @return int
     */
    public int modifyPayment(String userId, double modify_amount,String modify_usage,String modify_id){
        Payment payment = new Payment();
        payment.setId(modify_id);
        payment.setPay_amount(modify_amount);
        payment.setPay_usage(modify_usage);
        payment.setPay_date(new Date());
        PaymentDao paymentDao = new PaymentDaoImpl();
        return paymentDao.modifyPayment(userId,payment);
    }

    /**
     * 出账操作--修改--根据id批量删除数据
     * @param modify_ids
     * @return int
     */
    public int deletePayment(String modify_ids){
        Payment payment = new Payment();
        payment.setId(modify_ids);
        PaymentDao paymentDao = new PaymentDaoImpl();
        String[] delete = modify_ids.split(",");
        int delete_num = 0;
        for (int i = 0; i < delete.length; i++) {
            delete_num = paymentDao.deletePayment(payment);
        }
        return delete_num;
    }
}
