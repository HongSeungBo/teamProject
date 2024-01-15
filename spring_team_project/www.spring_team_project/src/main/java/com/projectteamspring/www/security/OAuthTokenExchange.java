package com.projectteamspring.www.security;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OAuthTokenExchange {

    public static void main(String[] args,String code) throws IOException {
        // OAuth 2.0 토큰 교환을 위한 엔드포인트 URL
        String url = "https://oauth2.googleapis.com/token";

        // 필수 매개변수 설정
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("client_id", "317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com"));
        params.add(new BasicNameValuePair("client_secret", "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"));
        params.add(new BasicNameValuePair("code", code));
        params.add(new BasicNameValuePair("grant_type", "authorization_code"));
        params.add(new BasicNameValuePair("redirect_uri", "https://localhost:8088/"));

        // HttpClient 초기화
        HttpClient httpClient = HttpClients.createDefault();

        // HTTP POST 요청 생성
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params));

        // 요청 전송 및 응답 수신
        HttpResponse response = httpClient.execute(httpPost);

        // 응답 확인
        if (response.getStatusLine().getStatusCode() == 200) {
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Access Token: " + responseBody);
        } else {
            System.out.println("Error: " + response.getStatusLine().getStatusCode() + ", " + response.getStatusLine().getReasonPhrase());
        }
    }
}
