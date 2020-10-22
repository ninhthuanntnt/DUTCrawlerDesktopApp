package com.ntnt.dutcrawler.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {

    private String token;
    private String additionalData;

    public JwtResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(String additionalData) {
        this.additionalData = additionalData;
    }
}
