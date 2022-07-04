package com.example.service.role;

import com.example.dao.BaseDao;
import com.example.dao.role.RoleDao;
import com.example.dao.role.RoleDaoImpl;
import com.example.dao.user.UserDao;
import com.example.pojo.Role;
import com.example.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RoleServiceImpl implements RoleService{


    private RoleDao roleDao;
    public RoleServiceImpl(){
        roleDao = new RoleDaoImpl();
    }

    @Override
    public List<Role> getRoleList() {
        Connection conn = null;
        List<Role> roleList = null;
        try{
            conn = BaseDao.Connection();
            roleList = roleDao.getRoleList(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            BaseDao.CloseResourse(conn,null,null);

        }
        return roleList;

    }


}
