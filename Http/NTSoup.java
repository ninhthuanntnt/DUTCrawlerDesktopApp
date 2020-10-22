package com.ntnt.dutcrawler.Http;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

public class NTSoup {

    public static HttpResponse connect(String url, String method, String body, Map<String, String> headers) {
        String[] seperatedUrl = sperateUrl(url);
        String domain = seperatedUrl[0];
        String path = seperatedUrl[1];
        String port = seperatedUrl[2];
        try {
            Socket socket = new Socket(InetAddress.getByName(domain), Integer.parseInt(port));

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            HttpRequest request = new HttpRequest();
            request.setPath(path);
            request.setMethod(method);

            if(headers!=null){
                for(Map.Entry<String, String> keyVal : headers.entrySet()){
                    request.putHeader(keyVal.getKey(), keyVal.getValue());
                }
            }

            request.setBody(body);
            writer.write(request.toString());
            writer.flush();
            String line;

            HttpResponse response = new HttpResponse();

            response.fetchRequestLineInfo(reader.readLine());
            // Fetch data from header
            while ((line = reader.readLine()) != null) {
                if(line.isEmpty())
                    break;

                String[] header = line.split(": ");
                response.setHeaders(header[0], header[1]);
            }
            // Fetch data from body
            StringBuilder resBody = new StringBuilder();
            char c;
            while((c = (char) reader.read()) != '\uFFFF'){
                resBody.append(c);
            }
            response.setBody(resBody.toString());

            socket.close();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpResponse get(String url, Map<String, String> header){
        return connect(url, "GET", "", header);
    }

    public static HttpResponse get(String url){
        return connect(url, "GET", "", null);
    }

    public static HttpResponse post(String url, String body, Map<String, String> header){
        return connect(url, "POST", body, header);
    }

    public static HttpResponse post(String url, String body){
        return connect(url, "POST", body, null);
    }

    private static String[] sperateUrl(String url) {
        String domain, path, port;

        url = url.replace("https://", "")
                .replace("http://", "");

        int i = url.indexOf("/");
        domain = url.substring(0, i);
        path = url.substring(i);

        // get port from domain
        int i1 = url.indexOf(":");
        if (i > 0) {
            port = url.substring(i1 + 1, i);
            domain = url.substring(0, i1);
        } else {
            port = "80";
        }

        return new String[]{domain, path, port};
    }
}
