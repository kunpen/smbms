package com.example.dao.user;

import com.example.dao.BaseDao;
import com.example.pojo.User;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    @Override
    public User getLoginUser(String userCode, Connection connection) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;


        if (connection!=null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params={userCode};


            try {

                resultSet = BaseDao.execute(connection,sql,params,resultSet,preparedStatement);
                if (resultSet.next()){
                    user = new User();

                    user.setId(resultSet.getInt("Id"));
                    user.setUserCode(resultSet.getString("userCode"));
                    user.setUserName(resultSet.getString("userName"));
                    user.setUserPassword(resultSet.getString("userPassword"));
                    user.setGender(resultSet.getInt("gender"));
                    user.setBrithday(resultSet.getDate("birthday"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setUserRole(resultSet.getInt("userRole"));
                    user.setCreatedBy(resultSet.getInt("createdBy"));
                    user.setCreationDate(resultSet.getDate("creationDate"));
                    user.setModifyby(resultSet.getInt("modifyBy"));
                    user.setModifyDate(resultSet.getDate("modifyDate"));




                }
                BaseDao.CloseResourse(connection,preparedStatement,resultSet);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return user;


    }
}
