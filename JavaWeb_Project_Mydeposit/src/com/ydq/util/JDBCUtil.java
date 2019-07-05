package com.ydq.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class JDBCUtil {
    private static  Connection connection =null;
    private static  PreparedStatement preparedStatement = null;
    private static  ResultSet resultSet = null;
    private static  String drivers = null;
    private static  String url = null;
    private static  String username = null;
    private static  String password = null;
    private int size = 10;
    private List<Connection> connectionList = new ArrayList<>();

    public JDBCUtil(int size) {
        this.size = size;
    }

    public JDBCUtil() {
    }

    static {
        InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        drivers = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

    }

    private Connection getConnection() {
        if (connectionList.isEmpty()){
            try {
                Class.forName(drivers);
                for (int i = 0; i < size; i++) {
                    connection = DriverManager.getConnection(url, username, password);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            connectionList.add(connection);
        }

        return connectionList.remove(0);
    }

    public int update(String sql, Object...params) {
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

    public List<Map<String, Object>> select (String sql, Object... params) {
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

    private void closeAll() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            preparedStatement.close();
            connectionList.add(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
