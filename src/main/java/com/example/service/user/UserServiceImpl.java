package com.example.service.user;

import com.example.dao.BaseDao;
import com.example.dao.user.UserDao;
import com.example.dao.user.UserDaoImpl;
import com.example.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();

    }

    @Override
    public User Login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try{
            connection = BaseDao.Connection();

            user = userDao.getLoginUser(userCode,connection);
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            BaseDao.CloseResourse(connection,null,null);

        }
        return user;


    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.Login("admin","1234567");
        System.out.println(admin.getUserName());

    }

}
