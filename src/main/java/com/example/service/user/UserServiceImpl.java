package com.example.service.user;

import com.example.dao.BaseDao;
import com.example.dao.user.UserDao;
import com.example.dao.user.UserDaoImpl;
import com.example.pojo.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

            user = userDao.getLoginUser(userCode,connection,password);

        }catch (Exception e){
            e.printStackTrace();

        }finally {
            BaseDao.CloseResourse(connection,null,null);

        }



        //
        return user;



    }

    @Override
    public boolean updateUserPasswd(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.Connection();
            if (userDao.updateUserPwd(connection,id,password)>0){
                flag = true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.CloseResourse(connection,null,null);
        }

        return flag;
    }

    @Override
    public int getUserCount(String Username, int UserRole) {
        Connection conn = null;
        int count=0;

        try{
            conn = BaseDao.Connection();
            count = userDao.GetUserCount(conn,Username,UserRole);


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.CloseResourse(conn,null,null);

        }


        return count;
    }

    @Override
    public List<User> getUserList(String QueryUserName, int QueryUserRole, int CurrentPageNo, int PageSize) {
        Connection conn = null;
        List<User> userList = null;
        System.out.println("query Username->"+QueryUserName);
        System.out.println("query userrole->"+QueryUserRole);
        System.out.println("current pageon->"+CurrentPageNo);
        System.out.println("pagesize --->"+PageSize);
        try{
            conn = BaseDao.Connection();
            userList = userDao.getUserList(conn, QueryUserName,QueryUserRole,CurrentPageNo,PageSize);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.CloseResourse(conn, null,null);
        }
        return userList;


    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        boolean flag = userService.updateUserPasswd(1,"123456");
        System.out.println(flag);


    }

    @Test
    public void test2(){
        UserServiceImpl userService = new UserServiceImpl();
        int userCount = userService.getUserCount(null,0);
        System.out.println(userCount);
    }

}
