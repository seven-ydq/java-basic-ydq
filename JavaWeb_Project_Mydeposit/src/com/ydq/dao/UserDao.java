package com.ydq.dao;

import com.ydq.model.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    List<Map<String, Object>> selectUserByUsernameAndPassword(String username,String password);
    List<Map<String, Object>> selectUserByUsername(User user);
    int addUser(User user);

}
