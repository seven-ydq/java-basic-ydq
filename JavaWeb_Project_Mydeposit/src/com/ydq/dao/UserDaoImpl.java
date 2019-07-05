package com.ydq.dao;

import com.ydq.model.User;

import com.ydq.util.JDBCUtilCopy;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    @Override
    public List<Map<String, Object>> selectUserByUsernameAndPassword(String username,String password) {
        String sql = "select * from user where username = ? and password = ?";
        Object[] params = {username,password};
        return JDBCUtilCopy.select(sql, params);
    }

    @Override
    public List<Map<String, Object>> selectUserByUsername(User user) {
        String sql = "select * from user where username = ?";
        Object[] params= {user.getUsername()};
        return JDBCUtilCopy.select(sql, params);
    }

    @Override
    public int addUser(User user) {
        String sql_insertUser = "INSERT INTO user (id, username, password) VALUES (?, ?, ?)";
        Object[] params = {user.getId(),user.getUsername(),user.getPassword()};
        return JDBCUtilCopy.update(sql_insertUser,params);
    }
}
