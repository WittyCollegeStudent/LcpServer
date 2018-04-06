package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBHelper {
    Connection con = null;
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

    public ResultSet getRS(String sql) throws SQLException {
        Statement s;
        s = con.createStatement();
        return s.executeQuery(sql);
    }

    public void doInsert(String sql) {
        Statement s;
        try {
            s = con.createStatement();
            s.executeUpdate(sql);
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