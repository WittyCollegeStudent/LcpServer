package servlet;

import entity.UserInfoView;
import org.json.JSONObject;
import service.LoginService;
import utils.DBHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        String tel = request.getParameter("tel");//获取传入的电话号码
        String passwd = request.getParameter("passwd");//获取传入的密码
        PrintWriter out = response.getWriter();
        DBHelper dbHelper = new DBHelper();
        dbHelper.conn();
        try {
            ResultSet resultSet = null;
            //根据传入参数查询问题
            resultSet = loginService.getUserInfo(dbHelper, tel, passwd);
            JSONObject jsonobj = new JSONObject();
            if (resultSet.next()) {
                jsonobj.put("id", resultSet.getString(UserInfoView.ID));
                jsonobj.put("type", resultSet.getString(UserInfoView.TYPE));
                jsonobj.put("major", resultSet.getString(UserInfoView.MAJOR));
                jsonobj.put("name", resultSet.getString(UserInfoView.NAME));
                jsonobj.put("sex", resultSet.getString(UserInfoView.SEX));
                jsonobj.put("tel", resultSet.getString(UserInfoView.TEL));
                jsonobj.put("success", true);
            } else {
                jsonobj.put("success", false);
            }
            out.println(jsonobj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.flush();
        dbHelper.close();
        out.close();
    }
}