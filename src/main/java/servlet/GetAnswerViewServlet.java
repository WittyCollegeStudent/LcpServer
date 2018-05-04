package servlet;

import entity.AnswerView;
import org.json.JSONArray;
import org.json.JSONObject;
import service.AnswerService;
import utils.DBHelper;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAnswerViewServlet extends HttpServlet {

    private AnswerService answerService = new AnswerService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        int qid = Integer.parseInt(request.getParameter("qid")); //问题编号
        PrintWriter out = response.getWriter();
        DBHelper dbHelper = new DBHelper();
        dbHelper.conn();
        try {
            ResultSet resultSet = answerService.searchAnswerView(dbHelper, qid, null, null);
            JSONArray jsonarray = new JSONArray();
            if(resultSet != null){
                while(resultSet.next()){
                    JSONObject jsonobj = new JSONObject();
                    jsonobj.put("id",resultSet.getString(AnswerView.ID));
                    jsonobj.put("qid",resultSet.getString(AnswerView.QID));
                    jsonobj.put("anscontent",resultSet.getString(AnswerView.ANSCONTENT));
                    jsonobj.put("respondant",resultSet.getString(AnswerView.RESPONDANT));
                    jsonobj.put("ans_date",resultSet.getString(AnswerView.ANS_DATE));
                    jsonobj.put("vote_p",resultSet.getString(AnswerView.VOTE_P));
                    jsonobj.put("vote_n",resultSet.getString(AnswerView.VOTE_N));
                    jsonarray.put(jsonobj);
                }
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
