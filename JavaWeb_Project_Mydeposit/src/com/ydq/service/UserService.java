package com.ydq.service;

import com.ydq.dao.UserDao;
import com.ydq.dao.UserDaoImpl;
import com.ydq.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UserService {
    public boolean select(String username) {
        User user = new User();
        user.setUsername(username);
        boolean flag = false;
        UserDao userDao = new UserDaoImpl();
        List<Map<String, Object>> list = userDao.selectUserByUsername(user);
        if (list != null && list.size() != 0) {
            flag = true;
            return flag;
        }
        return false;
    }

    public User login(String username,String password){
        UserDao userDao = new UserDaoImpl();
        List<Map<String, Object>>  list = userDao.selectUserByUsernameAndPassword(username,password);
        if (list != null && list.size() != 0) {
            Map<String, Object> map = list.get(0);
            return new User(map.get("id").toString(),map.get("username").toString(),map.get("password").toString());
        }
        return null;

    }


    public boolean addUser(String username,String password){
        boolean flag = false;
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(password);
        UserDao userDao = new UserDaoImpl();
        int count = userDao.addUser(user);
        if (count !=0){
            flag = true;
        }
        return false;
    }
}
