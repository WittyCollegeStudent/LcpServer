package servlet;

import org.json.JSONObject;
import service.LoginService;
import service.QuestionService;
import utils.DBHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

public class DelQuestionServlet extends HttpServlet {

    private QuestionService questionService = new QuestionService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        int question_id = Integer.parseInt(request.getParameter("question_id")); //回答编号
        String tel = request.getParameter("tel");//获取传入的电话号码
        String passwd = request.getParameter("passwd");//获取传入的密码
        PrintWriter out = response.getWriter();
        DBHelper dbHelper = new DBHelper();
        dbHelper.conn();
        try {
            ResultSet resultSet = new LoginService().getUserInfo(dbHelper, tel, passwd);
            JSONObject jsonobj = new JSONObject();
            if (resultSet.next()) {
                questionService.delQuestionById(dbHelper, question_id);
                jsonobj.put("success", true);
            } else {
                jsonobj.put("success", false);
                jsonobj.put("message", "删除问题失败");
            }
            out.println(jsonobj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.flush();
        dbHelper.close();
        out.close();
    }
}
