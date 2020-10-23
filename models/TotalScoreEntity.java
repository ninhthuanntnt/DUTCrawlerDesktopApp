package com.ntnt.dutcrawler.models;

public class TotalScoreEntity {

    private Long id;
    private String semester;
    private float totalCredit;
    private float restCredit;
    private float score1;
    private float score2;
    private float score3;
    private String resultType;
    private int activityScore;

    public TotalScoreEntity() {
    }

    public TotalScoreEntity(Long id, String semester,
                            float totalCredit, float restCredit,
                            float score1, float score2,
                            float score3, String resultType,
                            int activityScore) {
        this.id = id;
        this.semester = semester;
        this.totalCredit = totalCredit;
        this.restCredit = restCredit;
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
        this.resultType = resultType;
        this.activityScore = activityScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public float getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(float totalCredit) {
        this.totalCredit = totalCredit;
    }

    public float getRestCredit() {
        return restCredit;
    }

    public void setRestCredit(float restCredit) {
        this.restCredit = restCredit;
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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public int getActivityScore() {
        return activityScore;
    }

    public void setActivityScore(int activityScore) {
        this.activityScore = activityScore;
    }
}
