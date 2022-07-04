package com.example.dao.role;

import com.example.dao.BaseDao;
import com.example.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao{

    @Override
    public List<Role> getRoleList(Connection conn) throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (conn!=null){
            String sql = "select * from smbms_role";
            Object[] params = {};
            rs = BaseDao.execute(conn,sql,params,rs,pstm);
            while (rs.next()){
                Role _role = new Role();
                _role.setId(rs.getInt("id"));
                _role.setRoleCode(rs.getString("roleCode"));
                _role.setRoleName(rs.getString("roleName"));

                roles.add(_role);
            }
            BaseDao.CloseResourse(conn,null,null);

        }
        return roles;
    }
}
