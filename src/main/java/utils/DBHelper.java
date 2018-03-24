package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DBHelper dh = new DBHelper();
        dh.conn();

        try {
            ResultSet rs = dh.getRS("select * from tb_user");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println(id + name);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        dh.close();
        System.out.println("OK");

    }
}