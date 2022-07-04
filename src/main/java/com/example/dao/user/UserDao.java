package com.example.dao.user;

import com.example.pojo.Role;
import com.example.pojo.User;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public User getLoginUser(String usercode, Connection connection, String password);

    //update userpassword
    public  int updateUserPwd(Connection connection,int id,String passsword) throws SQLException;


    public int GetUserCount(Connection conn,String UserName,int UserRole) throws SQLException;
    public List<User> getUserList(Connection conn,String UserName,int UserRole,int CurrentPageNo,int PageSize) throws SQLException;



}
