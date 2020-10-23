package com.ntnt.dutcrawler.http;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private String body;
    private final Map<String, String> headers;
    private String protocol;
    private int status;

    public HttpResponse() {
        this.body = "";
        this.status = 200;
        this.headers = new HashMap<>();
        this.headers.put("Server", "NTNT HttpServer");
        this.headers.put("Connection", "keep-alive");
        this.headers.put("Content-Type", "application/json");
        this.headers.put("Access-Control-Allow-Origin", "http://localhost:3000");
        this.headers.put("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS");
        this.headers.put("Access-Control-Allow-Credentials", "true");
        this.headers.put("Access-Control-Max-Age", "240");
        String jsonDemo = "{\"a\" : \"123\"}";
        String CRLF = "\r\n";
    }

    public void setHeaders(String key, String value) {
        this.headers.put(key, value);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void fetchRequestLineInfo(String requestLine) {
        String[] infos = requestLine.split(" ");
        this.protocol = infos[0];
        this.status = Integer.parseInt(infos[1]);
    }

    public void fetchHeaderInfo(String headerInfo) {
        if (!headerInfo.isEmpty()) {
            String[] keyValue = headerInfo.split(":");
            this.headers.put(keyValue[0].trim(), keyValue[1].trim());
        }
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        String CRLF = "\r\n";
        response.append("HTTP/1.1")
                .append(" ")
                .append(this.status)
                .append(CRLF);

        this.headers.forEach((key, value) -> {
            response.append(key)
                    .append(": ")
                    .append(value)
                    .append(CRLF);
        });

        response.append("Content-Length: ")
                .append(this.body.getBytes().length)
                .append(CRLF)
                .append(CRLF)
                .append(this.body);

        return response.toString();
    }

}
