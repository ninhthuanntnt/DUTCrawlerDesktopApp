package com.ntnt.dutcrawler.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntnt.dutcrawler.http.HttpResponse;
import com.ntnt.dutcrawler.http.NTSoup;
import com.ntnt.dutcrawler.models.*;
import com.ntnt.dutcrawler.enums.NotiType;

import java.util.*;

public class AppService {
    private static AppService appService;
    private final String DOMAIN = "http://localhost:5000";

    public static AppService getInstance() {
        if (appService == null) {
            appService = new AppService();
        }
        return appService;
    }

    private AppService() {
    }

    public List<NotificationEntity> getNotifications(NotiType notiType, int page) {
        String url = DOMAIN + String.format("/api/notifications?notiType=%s&pageNumber=%d",
                notiType.toString().toLowerCase(),
                page);
        HttpResponse response = NTSoup.get(url);
        List<NotificationEntity> notifications;
        try {
            if (response != null) {
                String body = response.getBody();

                notifications = Arrays.asList(
                        new ObjectMapper().readValue(body, NotificationEntity[].class)
                );

                return notifications;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ScheduleEntity> getSchedules(String type, String token) {
        try {
            Map<String, String> header = new HashMap<>();
            header.put("Authorization", "Bearer " + token);
            HttpResponse response = NTSoup.get(DOMAIN + String.format("/api/schedule?type=%s", type), header);


            return Arrays.asList(new ObjectMapper().readValue(response.getBody(), ScheduleEntity[].class));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ScoreResponse getScores(String token){
        try{

            Map<String, String> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + token);

            HttpResponse response = NTSoup.get(DOMAIN + "/api/score", headers);
            ScoreResponse scoreResponses = new ObjectMapper().readValue(response.getBody(), ScoreResponse.class);
            return scoreResponses;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    public JwtResponse login(String username, String password) {
        try {

            Map<String, String> headers = new HashMap<>();
            HttpResponse response = NTSoup.post(DOMAIN + "/api/login",
                    String.format("{\"username\" : \"%s\",\"password\" : \"%s\"}", username, password), headers);

            JwtResponse jwtResponse = new ObjectMapper().readValue(response.getBody(), JwtResponse.class);
            System.out.println(jwtResponse);
            return jwtResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
