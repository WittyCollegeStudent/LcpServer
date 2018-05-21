package entity;

import java.io.Serializable;

public class QuestionView implements Serializable{

    public static final String VIEW_NAME = "QuestionView";
    public static final String COLUNM_NAME_ID = "m_id";
    public static final String COLUNM_NAME_QNAME = "v_qname";
    public static final String COLUNM_NAME_QCONTENT = "v_qcontent";
    public static final String COLUNM_NAME_PUBLISHER = "v_publisher";
    public static final String COLUNM_NAME_MAJOR = "v_major";
    public static final String COLUNM_NAME_ISVISIBLE = "v_isvisible";
    public static final String COLUNM_NAME_PUBDATE = "v_pubdate";
    public static final String COLUNM_NAME_COUNT = "count";
    public static final String COLUNM_NAME_PUBLISHER_ID = "publisher_id";

    private int id;
    private String qname;
    private String qcontent;
    private String publisher;
    private String major;
    private String isvisible;
    private String pubdate;
    private int count;
    private int publisher_id;

    public QuestionView() {
    }

    public QuestionView(int id, String qname, String qcontent, String publisher, String major, String isvisible, String pubdate, int count, int publisher_id) {
        this.id = id;
        this.qname = qname;
        this.qcontent = qcontent;
        this.publisher = publisher;
        this.major = major;
        this.isvisible = isvisible;
        this.pubdate = pubdate;
        this.count = count;
        this.publisher_id = publisher_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQcontent() {
        return qcontent;
    }

    public void setQcontent(String qcontent) {
        this.qcontent = qcontent;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    //输出格式为json
//    public String toString(){
//        return "{"
//                +"\"id\":" + id + ","
//                +"\"qname\":" + "\"" + qname +  "\"" + ","
//                +"\"qcontent\":" + "\"" + qcontent +  "\"" + ","
//                +"\"publisher\":" + "\"" + publisher +  "\"" + ","
//                +"\"major\":" + "\"" + major +  "\"" + ","
//                +"\"isvisible\":" + "\"" + isvisible +  "\"" + ","
//                +"\"pubdate\":" + "\"" + pubdate +  "\"" + ","
//                +"\"count\":" + count
//                +"}";
//    }
}
