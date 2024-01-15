package com.projectteamspring.www.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.projectteamspring.www.oauth.TokenDTO;
import com.projectteamspring.www.security.MemberVO;
import com.projectteamspring.www.service.MemberService;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/oauth/*")
@Slf4j
public class OauthController {
	
	@Inject
	private MemberService msv;
	
	@GetMapping(value = "/googleLogin")
	public ModelAndView googleLogin(){
		String CLIENT_ID = "317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com";
		String AUTHORIZE_URI = "https://accounts.google.com/o/oauth2/v2/auth";
		
		String redirect_uri = "http://localhost:8088/oauth/googleCallback";
		String response_type = "code";
		String scope= "email profile";
		
		String url = AUTHORIZE_URI + "?" +
				"client_id=" + CLIENT_ID +
				"&redirect_uri=" + redirect_uri + 
				"&response_type=" + response_type + 
				"&scope=" + scope;
		
		log.info(url);
		return new ModelAndView(new RedirectView(url));
	}
	
	@GetMapping("/googleCallback")
	public String googleCallback(String code,Model m) throws IOException, ParseException{
		log.info("googleCallback oauth");
		log.info(code);
		
		String tokenEndpoint = "https://www.googleapis.com/oauth2/v4/token";

        // 요청 파라미터
        String clientId = "317724378180-t5c623l108gi8b798s4omp4pkq4o2m73.apps.googleusercontent.com";
        String clientSecret = "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";
        String redirectUri = "http://localhost:8088/oauth/googleCallback";
        String grantType = "authorization_code";

        // URL 인코딩을 적용한 POST 요청 바디 데이터
        String postData = "code=" + code +
                "&client_id=" + clientId +
                "&client_secret=" + clientSecret +
                "&redirect_uri=" + redirectUri +
                "&grant_type=" + grantType;
        // HTTP POST 요청 보내기
        URL url = new URL(tokenEndpoint);
        log.info(url.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
            outputStream.write(postDataBytes);
        }

        // HTTP 응답 읽기
        int responseCode = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                (responseCode == HttpURLConnection.HTTP_OK) ? connection.getInputStream() : connection.getErrorStream()));

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        // 응답 출력
        System.out.println("Response Code: " + responseCode);
        System.out.println("Response Body: " + response.toString());

        connection.disconnect();
        
        TokenDTO token = jsonparse(response.toString());
        
        MemberVO mvo = getGoogletoken(token.getAccess_token());
        
        int isOK = msv.getEmail(mvo.getEmail());
        mvo.setPlatform("G");
        mvo.setPwd("qwertyuiop");
        m.addAttribute("mvo",mvo);
        if(isOK == 1) {
        	 return "/member/googleLogin";
        }else {
        	return "member/register";
		}
        
	}
	
	@PostMapping("/getGoogletoken")
	public MemberVO getGoogletoken(String accessToken) throws IOException, ParseException{
		log.info("getGoogletoken");

        // API 엔드포인트 URL
        String apiUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
        
        // access token, scope, 및 key를 URL에 포함하여 API 호출
        String apiEndpoint = apiUrl + "?access_token=";
       
        log.info(apiEndpoint);
		
		// HTTP GET 요청 보내기
		URL url = new URL(apiEndpoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Authorization", "Bearer " + accessToken);
		// HTTP 응답 읽기
		int responseCode = connection.getResponseCode();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				(responseCode == HttpURLConnection.HTTP_OK) ? connection.getInputStream() : connection.getErrorStream()));
		
		StringBuilder response = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			response.append(line);
		}
	
		// 응답 출력
		System.out.println("Response Code: " + responseCode);
		System.out.println("Response Body: " + response.toString());
		
		connection.disconnect();
		MemberVO mvo = new MemberVO();
		try {
			 // JSON 파서 생성
			 JSONParser parser = new JSONParser();
			 
			 // JSON 파싱
			 JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
			 
			 mvo.setEmail((String) jsonObject.get("email"));
			 mvo.setName((String) jsonObject.get("name"));
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		mvo.setPwd(" ");
		log.info(mvo.toString());
		return mvo;
	}
//	==================================================================================================================
	@GetMapping("/naver")
	public ModelAndView naver() {
		log.info("naver in OauthController");
		
		String AUTHORIZE_URI = "https://nid.naver.com/oauth2.0/authorize";
		
		String response_type = "code";
		
		String CLIENT_ID = "oK8TxRQic6qm3pRXmpDu";
		
		String STATE_STRING = "STATE_STRING";
		
		String redirect_uri = "http://localhost:8088/oauth/naverCallback";		
		
		String url = AUTHORIZE_URI + "?" +
				"response_type=" + response_type +
				"&client_id=" + CLIENT_ID + 
				"&state=" + STATE_STRING +
				"&redirect_uri=" + redirect_uri;
		
		log.info(url);
		
		return new ModelAndView(new RedirectView(url));
	}
	
	@GetMapping("/naverCallback")
	public void naverCallback(String code,String state) throws IOException, InterruptedException {
		log.info("naverCallback in OauthController");
		log.info(code);
		log.info(state);
		
		String AUTHORIZE_URI = "https://nid.naver.com/oauth2.0/token";
		
		String grant_type = "authorization_code";
		String CLIENT_ID = "oK8TxRQic6qm3pRXmpDu";
		String client_secret = "eeuY86KL1x";
		String STATE_STRING = "STATE_STRING";
		
		String url = AUTHORIZE_URI + "?" +
				"grant_type=" + grant_type +
				"&client_id=" + CLIENT_ID + 
				"&client_secret=" + client_secret+
				"&code=" + code +
				"&state=" + state;
		
		log.info(url);
		
		String json_date = getJsonData(url);
		log.info(json_date);
		TokenDTO token = jsonparse(json_date);
		
//		HttpClient client = HttpClient.newHttpClient();
//		
//		HttpRequest request = HttpRequest.newBuilder()
//				.uri(URI.create("https://openapi.naver.com/v1/nid/me"))
//				.GET()
//				.setHeader("Authorization", "Bearer "+token.getAccess_token())
//				.build();
//		
//		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
//		return new ModelAndView(new RedirectView(url));
	}
	
	 public String getJsonData(String url) {
		 RestTemplate restTemplate = new RestTemplate();
		 return restTemplate.getForObject(url, String.class);
	 }
	 
	 public TokenDTO jsonparse(String jsonString) {
		 TokenDTO token = new TokenDTO();
		 try {
			 // JSON 파서 생성
			 JSONParser parser = new JSONParser();
			 
			 // JSON 파싱
			 JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
			 
			 // 필요한 데이터 추출
			 token.setAccess_token((String) jsonObject.get("access_token"));
			 token.setExpires_in((long) jsonObject.get("expires_in"));
			 token.setId_token((String) jsonObject.get("id_token"));
			 token.setRefresh_token((String) jsonObject.get("refresh_token"));
			 token.setToken_type((String) jsonObject.get("token_type"));
			 token.setScope((String) jsonObject.get("scope"));
			 
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 log.info(token.toString());
		 return token;
	 }
}
