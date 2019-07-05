package com.ydq.model;

import java.util.Date;

public class Payment {
    private String id;
    private double pay_amount;
    private String pay_usage;
    private Date pay_date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(double pay_amount) {
        this.pay_amount = pay_amount;
    }

    public String getPay_usage() {
        return pay_usage;
    }

    public void setPay_usage(String pay_usage) {
        this.pay_usage = pay_usage;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }
}
