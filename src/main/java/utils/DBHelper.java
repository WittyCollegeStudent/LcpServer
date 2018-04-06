package utils;

import java.sql.*;

public class DBHelper {
    Connection con = null;
    public void conn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String uri = "jdbc:mysql://193.112.70.161";
            String user = "userlcp";
            String password = "userlcp";
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

    public void DoInsert(String sql) {
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