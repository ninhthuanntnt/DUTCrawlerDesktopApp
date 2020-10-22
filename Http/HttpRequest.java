package com.ntnt.dutcrawler.Http;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpRequest {
    private String method;
    private String path;
    private String protocol;
    private Map<String, String> headers;
    private Map<String, String> parameters;
    private String body;

    public HttpRequest() {
        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();
        this.putHeader("Content-Type","application/json");
        this.protocol = "HTTP/1.1";
        this.body = "";
    }

    public HttpRequest(String requestFrame) {
        String[] requestInfos = requestFrame.split("\r\n\r\n");
        String[] headerInfos = requestInfos[0].split("\r\n");
        String bodyInfos = (requestInfos.length == 2) ? requestInfos[1] : null;

        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();

        //get parameter from body
        if (bodyInfos != null) {
            this.fetchBodyInfo(bodyInfos);
        }
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMethod(String method) {
        this.method = method.toUpperCase();
    }

    public void putHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void fetchRequestLineInfo(String requestLine) {
        String[] infos = requestLine.split(" ");

        // extract parameter from path
        String path = infos[1];
        String realPath;
        String params = null;
        int indexOfQuestionMark = path.indexOf("?");
        if (indexOfQuestionMark >= 0) {
            realPath = path.substring(0, indexOfQuestionMark);
            params = path.substring(indexOfQuestionMark + 1);
        } else {
            realPath = path;
        }

        this.path = realPath.trim();
        this.method = infos[0].trim();
        this.protocol = infos[2].trim();

        //get parameter of get request
        if (params != null) {
            for (String data : params.split("&")) {
                String[] keyValue = data.split("=");
                this.parameters.put(keyValue[0], keyValue[1]);
            }
        }
    }

    public void fetchHeaderInfo(String headerInfo) {
        if (!headerInfo.isEmpty()) {
            String[] keyValue = headerInfo.split(":");
            this.headers.put(keyValue[0].trim(), keyValue[1].trim());
        }
    }

    public void fetchBodyInfo(String bodyInfo) {
        this.body = bodyInfo;

        String contentType = this.headers.get("Content-Type");
        if (!bodyInfo.isEmpty()) {
            if (contentType.contains("application/json")) {
                try {
                    JSONObject jsonObject = new JSONObject(bodyInfo);
                    Iterator<String> iterator = jsonObject.keys();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        this.parameters.put(key, String.valueOf(jsonObject.get(key)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getProtocol() {
        return protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeaders(String header) {
        return headers.get(header);
    }

    public String getParameter(String key) {
        return this.parameters.get(key);
    }

    @Override
    public String toString() {
        this.headers.put("Content-Length", String.valueOf(this.body.length()));

        StringBuilder request = new StringBuilder();
        String CRLF = "\r\n";

        request.append(this.getMethod())
                .append(" ")
                .append(this.path)
                .append(" ")
                .append(this.protocol)
                .append(CRLF);


        for (Map.Entry<String, String> header : this.headers.entrySet()) {
            request.append(header.getKey())
                    .append(": ")
                    .append(header.getValue())
                    .append(CRLF);
        }

        request.append(CRLF);
        request.append(this.body);

        return request.toString();
    }
}
