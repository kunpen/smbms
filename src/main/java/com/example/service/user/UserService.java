package com.example.service.user;
import com.example.pojo.Role;
import com.example.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User Login(String userCode,String password);
    public boolean updateUserPasswd(int id,String password) throws SQLException;
    public int getUserCount(String Username,int UserRole);
    public List<User> getUserList(String QueryUserName,int QueryUserRole,int CurrentPageNo,int PageSize);



}
