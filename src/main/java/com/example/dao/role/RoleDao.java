package com.example.dao.role;

import com.example.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao {
    public List<Role> getRoleList(Connection conn) throws SQLException;
}
