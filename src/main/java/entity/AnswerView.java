package entity;

public class AnswerView {

    public static final String VIEW_NAME = "AnswerView";
    public static final String COLUNM_NAME_ID = "m_id";
    public static final String COLUNM_NAME_QID = "m_qid";
    public static final String COLUNM_NAME_ANSCONTENT = "v_anscontent";
    public static final String COLUNM_NAME_RESPONDANT = "v_respondant";
    public static final String COLUNM_NAME_ANS_DATE = "v_ansdate";
    public static final String COLUNM_NAME_VOTE_P = "vote_p";
    public static final String COLUNM_NAME_VOTE_N = "vote_n";
    public static final String COLUNM_NAME_RESPONDANT_ID = "respondant_id";

    private int id;
    private int qid;
    private String anscontent;
    private String respondant;
    private String ans_date;
    private int vote_p;
    private int vote_n;
    private int respondant_id;

    public AnswerView(int id, int qid, String anscontent, String respondant, String ans_date, int vote_p, int vote_n, int respondant_id) {

        this.id = id;
        this.qid = qid;
        this.anscontent = anscontent;
        this.respondant = respondant;
        this.ans_date = ans_date;
        this.vote_p = vote_p;
        this.vote_n = vote_n;
        this.respondant_id = respondant_id;

    }

    public AnswerView() {
    }

    public int getRespondant_id() {
        return respondant_id;
    }

    public void setRespondant_id(int respondant_id) {
        this.respondant_id = respondant_id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getAnscontent() {
        return anscontent;
    }

    public void setAnscontent(String anscontent) {
        this.anscontent = anscontent;
    }

    public String getRespondant() {
        return respondant;
    }

    public void setRespondant(String respondant) {
        this.respondant = respondant;
    }

    public String getAns_date() {
        return ans_date;
    }

    public void setAns_date(String ans_date) {
        this.ans_date = ans_date;
    }

    public int getVote_p() {
        return vote_p;
    }

    public void setVote_p(int vote_p) {
        this.vote_p = vote_p;
    }

    public int getVote_n() {
        return vote_n;
    }

    public void setVote_n(int vote_n) {
        this.vote_n = vote_n;
    }
}
