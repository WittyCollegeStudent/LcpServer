package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.QuestionView;
import org.json.JSONObject;
import service.QuestionService;
import utils.DBHelper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddQuestionServlet extends HttpServlet {

    private QuestionService questionService = new QuestionService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = null;
        DBHelper dbHelper = new DBHelper();
        JSONObject jsonObject = new JSONObject();
        ResultSet resultSet = null;
        try {
            out = response.getWriter();
            dbHelper.conn();
            String qname = (URLDecoder.decode(request.getParameter("qname"), "utf-8"));
            resultSet = questionService.searchQuestionView(dbHelper, qname, null);
            //1.如果结果不为空，说明存在重名
            if (resultSet != null && resultSet.next()) {
                jsonObject.put("success", false);
                jsonObject.put("message", "存在重名问题！");
            } else {
                String qcontent = (URLDecoder.decode(request.getParameter("qcontent"), "utf-8")), publisher = (URLDecoder.decode(request.getParameter("publisher"), "utf-8")), major = (URLDecoder.decode(request.getParameter("major"), "utf-8")), isvisible = (URLDecoder.decode(request.getParameter("isvisible"), "utf-8"));
                Date day = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(day); //当前日期
                //2.插入新问题
                boolean success = questionService.insertQuestion(dbHelper, qname, qcontent, publisher, major, isvisible, date);
                jsonObject.put("success", success);
                if (success) {
                    //获取新建的问题
                    ResultSet reSet = questionService.searchQuestionView(dbHelper, qname, null);
                    reSet.next();
                    QuestionView questionView = new QuestionView();
                    questionView.setId(Integer.parseInt(reSet.getString(QuestionView.ID)));
                    questionView.setQname(reSet.getString(QuestionView.QNAME));
                    questionView.setQcontent(reSet.getString(QuestionView.QCONTENT));
                    questionView.setPublisher(reSet.getString(QuestionView.PUBLISHER));
                    questionView.setMajor(reSet.getString(QuestionView.MAJOR));
                    questionView.setIsvisible(reSet.getString(QuestionView.ISVISIBLE));
                    questionView.setPubdate(reSet.getString(QuestionView.PUBDATE));
                    questionView.setCount(Integer.parseInt(reSet.getString(QuestionView.COUNT)));
                    String jsonQuestionView = (new ObjectMapper()).writeValueAsString(questionView);
                    //3.如果插入成功，则应该返回这条问题
                    jsonObject.put(QuestionView.VIEW_NAME, jsonQuestionView);
                    jsonObject.put("message", "发布问题成功");
                }
            }
        } catch (IOException e) {
            jsonObject.put("success", false);
            jsonObject.put("message", "发布问题失败");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(out != null){
                out.println(jsonObject);
                out.close();
            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbHelper.close();
        }
    }
}

