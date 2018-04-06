package service;

import entity.AnswerView;
import utils.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AnswerService {

    public ResultSet getAnswerViewByQid(DBHelper dbHelper, int qid){
        try {
            return dbHelper.getRS("select * from lcp." + AnswerView.VIEW_NAME + " where " + AnswerView.QID + " = " + qid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
