package com.ntnt.dutcrawler.models;

public class UserEntity {
    private Long id;
    private String username;
    private String password;
    private String code;
    private String crawledWebCookie;

    public UserEntity() {
    }

    public UserEntity(Long id, String username, String password, String code, String crawledWebCookie) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.code = code;
        this.crawledWebCookie = crawledWebCookie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCrawledWebCookie() {
        return crawledWebCookie;
    }

    public void setCrawledWebCookie(String crawledWebCookie) {
        this.crawledWebCookie = crawledWebCookie;
    }
}

