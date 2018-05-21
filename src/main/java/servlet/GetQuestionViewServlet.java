package servlet;

import entity.QuestionView;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import service.QuestionService;
import utils.DBHelper;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetQuestionViewServlet extends HttpServlet {

    private QuestionService questionService = new QuestionService();

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        // 设置响应内容类型
        // 设置响应内容类型
        response.setContentType("text/json; charset=utf-8");
        String qName = request.getParameter("qName");//获取传入的问题名称参数
        String major = request.getParameter("major");//获取传入的问题
        PrintWriter out = response.getWriter();
        DBHelper dbHelper = new DBHelper();
        dbHelper.conn();
        try {
            ResultSet resultSet = null;
            if(StringUtils.isEmpty(qName) && StringUtils.isEmpty(major)){
                //查询出所有的问题
                resultSet = questionService.getAllQuestionView(dbHelper);
            }else{
                //根据传入参数查询问题
                resultSet = questionService.searchQuestionView(dbHelper, qName, major);
            }
            JSONArray jsonarray = new JSONArray();
            if(resultSet != null){
                while(resultSet.next()){
                    JSONObject jsonobj = new JSONObject();
                    jsonobj.put("id",resultSet.getString(QuestionView.COLUNM_NAME_ID));
                    jsonobj.put("qname",resultSet.getString(QuestionView.COLUNM_NAME_QNAME));
                    jsonobj.put("qcontent",resultSet.getString(QuestionView.COLUNM_NAME_QCONTENT));
                    jsonobj.put("publisher",resultSet.getString(QuestionView.COLUNM_NAME_PUBLISHER));
                    jsonobj.put("major",resultSet.getString(QuestionView.COLUNM_NAME_MAJOR));
                    jsonobj.put("isvisible",resultSet.getString(QuestionView.COLUNM_NAME_ISVISIBLE));
                    jsonobj.put("pubdate",resultSet.getString(QuestionView.COLUNM_NAME_PUBDATE));
                    jsonobj.put("count",resultSet.getString(QuestionView.COLUNM_NAME_COUNT));
                    jsonobj.put("publisher_id",resultSet.getString(QuestionView.COLUNM_NAME_PUBLISHER_ID));
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