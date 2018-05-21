package service;

import entity.Answer;
import entity.AnswerView;
import utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerService {

    /**
     * 查询回答
     * */
    public ResultSet searchAnswerView(DBHelper dbHelper, Integer qid, Integer id, String anscontent) {
        StringBuilder sql = new StringBuilder("select * from lcp.")
                .append(AnswerView.VIEW_NAME)
                .append(" where 1=1 ");
        List<Object> list = new ArrayList<Object>();
        if(qid != null){
            sql.append(" and ").append(AnswerView.COLUNM_NAME_QID).append("=?");
            list.add(qid);
        }
        if(id != null){
            sql.append(" and ").append(AnswerView.COLUNM_NAME_ID).append("=?");
            list.add(id);
        }
        if(anscontent != null){
            sql.append(" and ").append(AnswerView.COLUNM_NAME_ANSCONTENT).append("=?");
            list.add(anscontent);
        }
//        sql.append(" order by " + AnswerView.COLUNM_NAME_ANS_DATE + " desc," + AnswerView.COLUNM_NAME_VOTE_P + " asc," + AnswerView.COLUNM_NAME_ANSCONTENT + " asc");
        sql.append(" order by " + AnswerView.COLUNM_NAME_ANS_DATE + " desc," + AnswerView.COLUNM_NAME_ID + " asc");
        return dbHelper.getRS(sql.toString(), list);
    }

    /**
     * 删除回答
     * */
    public void delAnswerById(DBHelper dbHelper, Integer aid) {
        StringBuilder sql = new StringBuilder("delete from lcp.")
                .append(Answer.TABLE_NAME)
                .append(" where 1=1 ");
        List<Object> list = new ArrayList<Object>();
        if(aid != null){
            sql.append(" and ").append(AnswerView.COLUNM_NAME_ID).append("=?");
            list.add(aid);
        }
//        sql.append(" order by " + AnswerView.COLUNM_NAME_ANS_DATE + " desc," + AnswerView.COLUNM_NAME_VOTE_P + " asc," + AnswerView.COLUNM_NAME_ANSCONTENT + " asc");
        sql.append(" order by " + AnswerView.COLUNM_NAME_ANS_DATE + " desc," + AnswerView.COLUNM_NAME_ID + " asc");
        dbHelper.doDelete(sql.toString(), list);
    }

    /**
     * 返回上一次成功插入后的id
     * */
    public int lastInsertID(DBHelper dbHelper){
        int id = -1;//结果
        String sql = "SELECT LAST_INSERT_ID();";
        ResultSet resultSet = dbHelper.getRS(sql, null);
        if(resultSet != null){
            try {
                resultSet.next();
                id = Integer.parseInt(resultSet.getString("LAST_INSERT_ID()"));
            } catch (SQLException e) {
                e.printStackTrace();
                id = -1;
            }
        }
        System.out.println("last_insert_id = " + id);
        return id;
    }

    /**
     * 插入一条回答
     */
    public boolean insertAnswer(DBHelper dbHelper, Integer qid, String anscontent, String respondant, String ansdate) {
        boolean flag = false;
        try {
            String sqlInsert = "insert into lcp.Answer(m_qid, v_anscontent, m_respondant, v_ansdate) values(?, ?, ?, ?);";
            List<Object> params = new ArrayList<Object>();
            params.add(qid);
            params.add(anscontent);
            params.add(respondant);
            params.add(ansdate);
            dbHelper.doInsert(sqlInsert, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
