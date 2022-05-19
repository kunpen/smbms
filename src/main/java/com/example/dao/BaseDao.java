package com.example.dao;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver      = properties.getProperty("driver");
        url         = properties.getProperty("url");
        username    = properties.getProperty("username");
        password    = properties.getProperty("password");
    }
    //get mysql conn
    public static Connection Connection(){
        Connection connection = null;
        try {
            Class.forName(driver);
            //
            connection = DriverManager.getConnection(url,username,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  connection;

    }
    //query
    public static ResultSet execute(Connection connection,String sql,Object[] params,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1, params[i]);

        }



        resultSet = preparedStatement.executeQuery();
        return resultSet;


    }

    //update
    public static int execute(Connection connection,String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i+1,params[i]);

        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;


    }




    //del

    //instert


    //release
    public static boolean CloseResourse(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        boolean flag = true;
        if (resultSet!=null){
            try{
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement!=null){
            try{
                preparedStatement.close();
            }catch (SQLException e){
                e.printStackTrace();
                flag = false;

            }

        }
        if (connection!=null){
            try{
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
                flag =false;
            }
        }

        return flag;

    }




}



