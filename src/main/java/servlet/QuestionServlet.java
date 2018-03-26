package servlet;

import entity.QuestionView;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.DBHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
public class QuestionServlet extends HttpServlet {
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
    throws ServletException, IOException
    {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        DBHelper dbHelper = new DBHelper();
        dbHelper.conn();
        try {
            ResultSet resultSet = dbHelper.getRS("select * from lcp." + QuestionView.VIEW_NAME);
            JSONArray jsonarray = new JSONArray();
            while(resultSet.next()){
                JSONObject jsonobj = new JSONObject();
                jsonobj.put("id",resultSet.getString(QuestionView.ID));
                jsonobj.put("qname",resultSet.getString(QuestionView.QNAME));
                jsonobj.put("qcontent",resultSet.getString(QuestionView.QCONTENT));
                jsonobj.put("publisher",resultSet.getString(QuestionView.PUBLISHER));
                jsonobj.put("major",resultSet.getString(QuestionView.MAJOR));
                jsonobj.put("isvisible",resultSet.getString(QuestionView.ISVISIBLE));
                jsonobj.put("pubdate",resultSet.getString(QuestionView.PUBDATE));
                jsonobj.put("count",resultSet.getString(QuestionView.COUNT));
                jsonarray.put(jsonobj);
            }
            out.println(jsonarray);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.flush();
        dbHelper.close();
        out.close();
    }
}