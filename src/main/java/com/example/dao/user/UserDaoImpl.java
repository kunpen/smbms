package com.example.dao.user;

import com.example.dao.BaseDao;
import com.example.pojo.Role;
import com.example.pojo.User;
import com.example.service.user.UserServiceImpl;
import com.mysql.cj.util.StringUtils;
import sun.util.resources.ext.CurrencyNames_ar_OM;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao{

    @Override
    public User getLoginUser(String userCode, Connection connection,String password) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        User user = null;


        if (connection!=null){
            String sql = "select * from smbms_user where userCode=? AND userPassword =?";
            Object[] params={userCode,password};


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


    @Override
    public int updateUserPwd(Connection connection, int id, String passsword) throws SQLException {
        int exec = 0;
        if (connection!=null){
            String sql = "update smbms_user set userPassword=? where id=?";
            PreparedStatement pstm = null;
            Object[] parms = {passsword,id};
            exec =  BaseDao.execute(connection,sql,parms,pstm);

            BaseDao.CloseResourse(connection,pstm,null);
        }
        return exec;

    }

    @Override
    public int GetUserCount(Connection conn, String UserName, int UserRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count=0;
        if (conn!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id");
            ArrayList<Object> list = new ArrayList<Object>();

            if (!StringUtils.isNullOrEmpty(UserName)){
                sql.append(" and u.userName like ?");
                list.add("%"+UserName+"%");
            }
            if (UserRole>0){
                sql.append(" and u.userRole = ?");
                list.add(UserRole);
            }
            Object[] params = list.toArray();

            System.out.println("userDaoImpl-->GetUserCount:"+sql.toString());
            rs = BaseDao.execute(conn,sql.toString(),params, rs, pstm);
            if (rs.next()){
                count = rs.getInt("count");
            }
            BaseDao.CloseResourse(null,pstm,rs);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection conn, String UserName, int UserRole, int CurrentPageNo, int PageSize) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userlist = new ArrayList<>();
        if (conn!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.RoleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<>();
            if (StringUtils.isNullOrEmpty(UserName)){
                sql.append(" and u.userName like ?");
                list.add("%"+ UserName+"%");

            }
            if (UserRole>0){
                sql.append(" and u.userRole = ?");
                list.add(UserRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            CurrentPageNo = (CurrentPageNo-1)*PageSize;
            list.add(CurrentPageNo);
            list.add(PageSize);

            System.out.println("sql-->:"+sql);
            Object[] params = list.toArray();
            rs=BaseDao.execute(conn,sql.toString(),params,rs,pstm);
            while (rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("Id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setUserPassword(rs.getString("userPassword"));
                _user.setGender(rs.getInt("gender"));
                _user.setBrithday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setAddress(rs.getString("address"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setCreatedBy(rs.getInt("createdBy"));
                _user.setCreationDate(rs.getDate("creationDate"));
                _user.setModifyby(rs.getInt("modifyBy"));
                _user.setModifyDate(rs.getDate("modifyDate"));
                userlist.add(_user);

            }
            BaseDao.CloseResourse(null,pstm,rs);

        }


        return userlist;
    }



}
