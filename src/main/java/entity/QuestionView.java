package entity;

import java.io.Serializable;

public class QuestionView implements Serializable{

    public static final String VIEW_NAME = "QuestionView";
    public static final String ID = "m_id";
    public static final String QNAME = "v_qname";
    public static final String QCONTENT = "v_qcontent";
    public static final String PUBLISHER = "v_publisher";
    public static final String MAJOR = "v_major";
    public static final String ISVISIBLE = "v_isvisible";
    public static final String PUBDATE = "v_pubdate";
    public static final String COUNT = "count";

    public int id;
    public String name;
    public String qcontent;
    public String publisher;
    public String major;
    public String isvisible;
    public String pubdate;
    public int count;

    public QuestionView() {
    }

    public QuestionView(int id, String name, String qcontent, String publisher, String major, String isvisible, String pubdate, int count) {
        this.id = id;
        this.name = name;
        this.qcontent = qcontent;
        this.publisher = publisher;
        this.major = major;
        this.isvisible = isvisible;
        this.pubdate = pubdate;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
