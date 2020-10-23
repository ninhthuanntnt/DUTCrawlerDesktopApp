package com.ntnt.dutcrawler.models;

public class ScheduleEntity {
    private long id;
    private String code;
    private String name;
    private float credit;
    private float tuition;
    private boolean payed;
    private String lecturer;
    private String schedule;
    private String studyingWeek;

    public ScheduleEntity() {
    }

    public ScheduleEntity(long id, String code,
                          String name, float credit,
                          float tuition, boolean payed,
                          String lecturer, String schedule,
                          String studyingWeek) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credit = credit;
        this.tuition = tuition;
        this.payed = payed;
        this.lecturer = lecturer;
        this.schedule = schedule;
        this.studyingWeek = studyingWeek;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public float getTuition() {
        return tuition;
    }

    public void setTuition(float tuition) {
        this.tuition = tuition;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStudyingWeek() {
        return studyingWeek;
    }

    public void setStudyingWeek(String studyingWeek) {
        this.studyingWeek = studyingWeek;
    }
}
