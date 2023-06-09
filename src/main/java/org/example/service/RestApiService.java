package org.example.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApiService {
    public String getJsonResponse(String ApiUrl) {
        String responseString = "";
        try {
            URL url = new URL(ApiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET"); // http 메서드
            connection.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            connection.setDoOutput(true); // default false
            Integer httpStatusCode = connection.getResponseCode();

            if(httpStatusCode < 200 || httpStatusCode >= 300) {
                throw new RuntimeException("Http status:" + httpStatusCode);
            }

            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while(line != null) { // br is not empty
                sb.append(line);
                line = br.readLine();
            }
            connection.disconnect();

            responseString += sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
