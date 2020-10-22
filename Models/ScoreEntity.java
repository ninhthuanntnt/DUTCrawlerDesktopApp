package com.ntnt.dutcrawler.Models;


public class ScoreEntity {
    private long id;
    private String semester;
    private String code;
    private String subject;
    private float credit;
    private float score1;
    private float score2;
    private float score3;
    private float score4;
    private float score5;
    private float score6;
    private float score7;
    private float score8;
    private String score9;

    public ScoreEntity() {
    }

    public ScoreEntity(long id, String semester,
                       String code, String subject,
                       float credit, float score1,
                       float score2, float score3,
                       float score4, float score5,
                       float score6, float score7,
                       float score8, String score9) {
        this.id = id;
        this.semester = semester;
        this.code = code;
        this.subject = subject;
        this.credit = credit;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.score4 = score4;
        this.score5 = score5;
        this.score6 = score6;
        this.score7 = score7;
        this.score8 = score8;
        this.score9 = score9;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getScore1() {
        return score1;
    }

    public void setScore1(float score1) {
        this.score1 = score1;
    }

    public float getScore2() {
        return score2;
    }

    public void setScore2(float score2) {
        this.score2 = score2;
    }

    public float getScore3() {
        return score3;
    }

    public void setScore3(float score3) {
        this.score3 = score3;
    }

    public float getScore4() {
        return score4;
    }

    public void setScore4(float score4) {
        this.score4 = score4;
    }

    public float getScore5() {
        return score5;
    }

    public void setScore5(float score5) {
        this.score5 = score5;
    }

    public float getScore6() {
        return score6;
    }

    public void setScore6(float score6) {
        this.score6 = score6;
    }

    public float getScore7() {
        return score7;
    }

    public void setScore7(float score7) {
        this.score7 = score7;
    }

    public float getScore8() {
        return score8;
    }

    public void setScore8(float score8) {
        this.score8 = score8;
    }

    public String getScore9() {
        return score9;
    }

    public void setScore9(String score9) {
        this.score9 = score9;
    }
}
