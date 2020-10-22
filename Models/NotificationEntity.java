package com.ntnt.dutcrawler.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ntnt.dutcrawler.enums.NotiType;

import java.util.Date;

public class NotificationEntity {
    private Long id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;
    private String title;
    private String content;
    private NotiType type;

    public NotificationEntity() {
    }

    public NotificationEntity(Long id, Date date, String title, String content, NotiType type) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NotiType getType() {
        return type;
    }

    public void setType(NotiType type) {
        this.type = type;
    }
}
