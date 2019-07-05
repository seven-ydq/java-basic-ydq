package com.ydq.util;

import java.util.UUID;

public class T {
    public static void method_copy(int count){
        String sql = "insert into user(id, username, password) values(?, ?, ?)";
        int number = 0;
        long beginTime = System.currentTimeMillis();
        while (count > 0) {
            count--;
            Object[] params = {UUID.randomUUID().toString(),"tim","123"};
            number = JDBCUtilCopy.update(sql,params);

        }
        long endTime = System.currentTimeMillis();
        System.out.println("一般的JDBC方法花费的时间为：" + (endTime - beginTime));
    }

    public static void method(int count){
        JDBCUtil jdbcUtil = new JDBCUtil();
        String sql = "insert into user(id, username, password) values(?, ?, ?)";
        int number = 0;
        long beginTime = System.currentTimeMillis();
        while (count > 0) {
            count--;
            Object[] params = {UUID.randomUUID().toString(),"tim","123"};
            number = jdbcUtil.update(sql,params);

        }
        long endTime = System.currentTimeMillis();
        System.out.println("数据库连接池花费的时间为：" + (endTime - beginTime));
    }

    public static void method_c3p0(int count){
        String sql = "insert into user(id, username, password) values(?, ?, ?)";
        int number = 0;
        long beginTime = System.currentTimeMillis();
        while (count > 0) {
            count--;
            Object[] params = {UUID.randomUUID().toString(),"tim","123"};
            number = JDBCUtil_c3p0.update(sql,params);

        }
        long endTime = System.currentTimeMillis();
        System.out.println("数据库c3p0连接池花费的时间为：" + (endTime - beginTime));
    }

    public static void main(String[] args) {
        /*method_copy(1000);
        method(1000);*/
        method_c3p0(1000);



    }
}
