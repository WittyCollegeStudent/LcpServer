package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class DBHelper {
    private Connection con = null;
    public void conn() {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = this.getClass().getClassLoader()
                    .getResourceAsStream("jdbc.properties");
            properties.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            String driver = properties.getProperty("datasource.driver");
            String uri = properties.getProperty("datasource.url");
            String user = properties.getProperty("datasource.username");
            String password = properties.getProperty("datasource.password");
            Class.forName(driver);
            con = DriverManager.getConnection(uri, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getRS(String sql, List<Object> params) {
        PreparedStatement preState = null;//预编译块
        ResultSet resultSet = null;
        try {
            preState = con.prepareStatement(sql);
            if(params != null){
                int size = params.size();
                for(int i = 0 ; i < size; i ++){
                    Object item = params.get(i);
                    //判断item类型，对相应的类型选择不同的方法
                    if(item instanceof String){
                        preState.setString(i + 1, item.toString());
                    }else{
                        preState.setInt(i + 1, Integer.parseInt(item.toString()));
                    }
                }
            }
            resultSet = preState.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void doInsert(String sql, List<Object> params) {
        PreparedStatement preState = null;//预编译块
        try {
            preState = con.prepareStatement(sql);
            int size = params.size();//参数数量
            for(int i  = 0 ; i < size ; i ++){
                Object item = params.get(i);
                //判断item类型，对相应的类型选择不同的方法
                if(item instanceof String){
                    preState.setString(i + 1, item.toString());
                }else{
                    preState.setInt(i + 1, Integer.parseInt(item.toString()));
                }
            }
            preState.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}