package com.example.dao.user;

import com.example.pojo.User;

import java.sql.Connection;

public interface UserDao {
    public User getLoginUser(String usercode, Connection connection);
}
