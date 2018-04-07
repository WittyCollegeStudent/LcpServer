package service;

import entity.QuestionView;
import utils.DBHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QuestionService {

    /**
     * 获得所有的问题，并按照评论数量降序排序
     */
    public ResultSet getAllQuestionView(DBHelper dbHelper) {
        return dbHelper.getRS("select * from lcp." + QuestionView.VIEW_NAME + " order by count desc,v_qname asc", null);
    }

    /**
     * 根据条件查询问题（模糊匹配）
     */
    public ResultSet searchQuestionView(DBHelper dbHelper, String qname, String major) {
        StringBuilder sql = new StringBuilder("select * from lcp." + QuestionView.VIEW_NAME + " where 1=1 ");
        List<Object> params = new ArrayList<Object>();
        //问题模糊匹配
        if (qname != null) {
            sql.append("and v_qname like ?");
            params.add("%" + qname + "%");
        }
        //行业精确匹配
        if (major != null) {
            sql.append(" and v_major = ?");
            params.add(major);
        }
        sql.append(" order by count desc,v_qname asc");
        return dbHelper.getRS(sql.toString(), params);
    }

    /**
     * 插入一条问题
     */
    public boolean insertQuestion(DBHelper dbHelper, String qname, String qcontent, String publisher, String major
            , String isvisible, String date) {
        boolean flag = false;
        try {
            String sqlInsert = "insert into lcp.Question(v_qname, v_qcontent, m_publisher, t_major, t_isvisible, v_pubdate) values(?,?,?,?,?,?);";
            List<Object> params = new ArrayList<Object>();
            params.add(qname);
            params.add(qcontent);
            params.add(publisher);
            params.add(major);
            params.add(isvisible);
            params.add(date);
            dbHelper.doInsert(sqlInsert, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
