package com.ydq.util;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.*;
import java.util.*;

public class JDBCUtil_c3p0 {
    private static Connection connection =null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    private static Connection getConnection() {
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static int update(String sql, Object...params) {
        int result = 0;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length != 0) {
                for(int i = 0 ; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            result = preparedStatement.executeUpdate();
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Map<String, Object>> select (String sql, Object... params) {
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            if (params != null && params.length != 0) {
                for(int i = 0 ; i < params.length; i++) {
                    preparedStatement.setObject(i + 1, params[i]);
                }
            }
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> map = new HashMap<>();
                ResultSetMetaData metaData = resultSet.getMetaData();
                int colum = metaData.getColumnCount();//获取列的数量
                for (int i = 1; i <= colum; i++) {
                    String columName = metaData.getColumnName(i);//获取列名
                    map.put(columName, resultSet.getObject(columName));
                }
                list.add(map);
            }
            closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static void closeAll() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
