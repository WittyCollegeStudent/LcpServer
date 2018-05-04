package service;

import entity.UserInfoView;
import utils.DBHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginService {

    /**
     * 根据用户名密码查询用户信息
     * */
    public ResultSet getUserInfo(DBHelper dbHelper, String tel, String passwd) {
        StringBuilder sql = new StringBuilder("select * from lcp.")
                .append(UserInfoView.VIEW_NAME)
                .append(" where 1=1 ");
        List<Object> list = new ArrayList<Object>();
        if(tel != null){
            sql.append(" and ").append(UserInfoView.TEL).append("=?");
            list.add(tel);
        }
        if(passwd != null){
            sql.append(" and ").append(UserInfoView.PASSWD).append("=?");
            list.add(passwd);
        }
        return dbHelper.getRS(sql.toString(), list);
    }

}
