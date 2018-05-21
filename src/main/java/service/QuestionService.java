package service;

import entity.Answer;
import entity.AnswerView;
import entity.Quesion;
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
        return dbHelper.getRS("select * from lcp." + QuestionView.VIEW_NAME + " order by count desc,m_id asc", null);
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
        sql.append(" order by count desc,m_id asc");
        return dbHelper.getRS(sql.toString(), params);
    }

    /**
     * 查询回答
     * */
    public void delQuestionById(DBHelper dbHelper, Integer qid) {
        StringBuilder sql = new StringBuilder("delete from lcp.")
                .append(Quesion.TABLE_NAME)
                .append(" where 1=1 ");
        List<Object> list = new ArrayList<Object>();
        if(qid != null){
            sql.append(" and ").append(Quesion.ID).append("=?");
            list.add(qid);
        }
//        sql.append(" order by " + AnswerView.COLUNM_NAME_ANS_DATE + " desc," + AnswerView.COLUNM_NAME_VOTE_P + " asc," + AnswerView.COLUNM_NAME_ANSCONTENT + " asc");
        sql.append(" order by " + Quesion.PUBDATE + " desc," + Quesion.ID + " asc");
        dbHelper.doDelete(sql.toString(), list);
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
