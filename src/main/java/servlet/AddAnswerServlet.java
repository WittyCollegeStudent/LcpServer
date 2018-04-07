package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.AnswerView;
import org.json.JSONObject;
import service.AnswerService;
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

public class AddAnswerServlet extends HttpServlet {

    private AnswerService answerService = new AnswerService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        PrintWriter out = null;
        DBHelper dbHelper = new DBHelper();
        JSONObject jsonObject = new JSONObject();
        ResultSet reSet = null;
        try {
            out = response.getWriter();
            dbHelper.conn();
            int qid = Integer.parseInt(URLDecoder.decode(request.getParameter("qid"), "utf-8"));
            String anscontent = (URLDecoder.decode(request.getParameter("anscontent"), "utf-8")), respondant = (URLDecoder.decode(request.getParameter("respondant"), "utf-8"));
            Date day = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String date = format.format(day); //当前日期
            //2.插入新回答
            boolean success = answerService.insertAnswer(dbHelper, qid, anscontent, respondant, date);
            jsonObject.put("success", success);
            if (success) {
                //获取新建的回答id
                int id = answerService.lastInsertID(dbHelper);
                reSet = answerService.searchAnswerView(dbHelper, null, id, null);
                reSet.next();
                AnswerView answerView = new AnswerView();
                answerView.setId(Integer.parseInt(reSet.getString(AnswerView.ID)));
                answerView.setQid(Integer.parseInt(reSet.getString(AnswerView.QID)));
                answerView.setAns_date(reSet.getString(AnswerView.ANS_DATE));
                answerView.setRespondant(reSet.getString(AnswerView.RESPONDANT));
                answerView.setAnscontent(reSet.getString(AnswerView.ANSCONTENT));
                answerView.setVote_p(Integer.parseInt(reSet.getString(AnswerView.VOTE_P)));
                answerView.setVote_n(Integer.parseInt(reSet.getString(AnswerView.VOTE_N)));
                String jsonanswerView = (new ObjectMapper()).writeValueAsString(answerView);
                //3.如果插入成功，则应该返回这条问题
                jsonObject.put(AnswerView.VIEW_NAME, jsonanswerView);
                jsonObject.put("message", "添加回答成功");
            }
        } catch (IOException e) {
            jsonObject.put("success", false);
            jsonObject.put("message", "添加回答失败");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.println(jsonObject);
                out.close();
            }
            if (reSet != null) {
                try {
                    reSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbHelper.close();
        }
    }
}

