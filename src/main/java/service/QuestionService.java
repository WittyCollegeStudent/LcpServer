package service;

import entity.QuestionView;
import utils.DBHelper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionService {

    /**
     * 获得所有的问题，并按照评论数量降序排序
     * */
    public ResultSet getAllQuestionView(DBHelper dbHelper){
        try {
            return dbHelper.getRS("select * from lcp." + QuestionView.VIEW_NAME + " order by count desc,v_qname asc");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据条件查询问题（模糊匹配）
     * */
    public ResultSet searchQuestionView(DBHelper dbHelper, String qname, String major){
        try {
            StringBuilder sql = new StringBuilder("select * from lcp." + QuestionView.VIEW_NAME + " where 1 = 1 ");
            //问题模糊匹配
            if(qname != null){
                sql.append("and v_qname like \"%" + qname + "%\"");
            }
            //行业精确匹配
            if(major != null){
                sql.append("and v_major = \"" + major + "\"");
            }
            sql.append(" order by count desc,v_qname asc");
            return dbHelper.getRS(sql.toString());
        } catch (SQLException e) {
            System.out.println("不存在以\"" + qname + "\"为名的问题");
        }
        return null;
    }

    /**
     * 插入一条问题
     * */
    public boolean insertQuestion(DBHelper dbHelper, String qname, String qcontent, String publisher, String major
            , String isvisible, String date){
        boolean flag = false;
        try{
            StringBuffer sqlInsert = (new StringBuffer("insert into lcp.Question(v_qname, v_qcontent, m_publisher, t_major" + ", t_isvisible, v_pubdate) values("))
                    .append("\"").append(qname).append("\"").append(",")
                    .append("\"").append(qcontent).append("\"").append(",")
                    .append(publisher).append(",")
                    .append(major).append(",")
                    .append(isvisible).append(",")
                    .append("\"").append(date).append("\"").append(");");
            dbHelper.doInsert(sqlInsert.toString());
            flag = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

}
