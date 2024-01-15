package com.projectteamspring.www.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projectteamspring.www.domain.PayMentVO;
import com.projectteamspring.www.domain.RefundVO;
import com.projectteamspring.www.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/payment/*")
@Controller
public class PaymentController {
	
	@Inject
	private PaymentService psv;
	
	@GetMapping("/refundList")
	public String gorefundList(Model m) {
		
		List<RefundVO> list = psv.getList();
		m.addAttribute("list", list);
		for(RefundVO rvo : list) {
			rvo.setPmvo(psv.setPayment(rvo.getUid()));
		}
		return "/shop/refundList";
	}
	
	@PostMapping(value="/kakaopay", consumes = "application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> kakaoPay(@RequestBody PayMentVO pmvo){
		int isOk = psv.successPayment(pmvo);
		return null;
	}
	
	@PostMapping(value="/callback",consumes = "application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> callback(@RequestBody PayMentVO pmvo) {
		log.info("callback");
		log.info(pmvo.toString());
		int isOK = psv.insertPayment(pmvo);
		return isOK > 0 ? 
			new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/myPayment")
	public String gomyPayment(String email,Model m) {
		log.info(email);
		List<PayMentVO> list = psv.selectMyPayment(email);
		m.addAttribute("list",list);
		return "/shop/myPayment";
	}
	
	@PostMapping(value="/refundApplication",consumes = "application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> refundApplication(@RequestBody RefundVO rvo){
		
		int isOK = psv.refundApplication(rvo);
		
		return isOK > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : 
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	@PostMapping(value="/setPayment/{merchantUid}",consumes = "application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setPayment(@PathVariable String merchantUid) throws IOException {
		log.info(merchantUid);
		PayMentVO pmvo = psv.setPayment(merchantUid);
		RefundVO rvo = psv.getRefund(merchantUid);
		rvo.setPmvo(pmvo);
		
		String tokenEndpoint = "https://api.iamport.kr/users/getToken";

        // 요청 파라미터
        String imp_key = "4285004305815010";
        String imp_secret = "ey9bfN35Rg2Kq1HnLFF3uXhQ5mcnvqA6772NxLCc82RNdxpb1gsdGdfmsadM24KvmfFYESXKDObwrD5I";
        // URL 인코딩을 적용한 POST 요청 바디 데이터
        String postData = "imp_key=" + imp_key +
                "&imp_secret=" + imp_secret;
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
        
        try {
			 // JSON 파서 생성
			 JSONParser parser = new JSONParser();
			 
			 // JSON 파싱
			 JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
			 
			 Object Jsonresponse = jsonObject.get("response");
			 log.info("response =  "+Jsonresponse.toString());
			 
			 JSONObject jsonObject2 = (JSONObject) parser.parse(Jsonresponse.toString());
			 String access_token = (String) jsonObject2.get("access_token");
			 log.info("access_token = " + access_token);
			 
			 rvo.setAccess_token(access_token);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		
        int isOK = paymentsCancel(rvo);
		
        if(isOK == 1) {
//        	isOK *= psv.deletePayments(pmvo);
        	return new ResponseEntity<String>("1", HttpStatus.OK);
        }
		return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@PostMapping(value="/cancel")
	public int paymentsCancel(RefundVO rvo) throws IOException {

        // 요청 파라미터
		String imp_uid = rvo.getUid();
		long amount = rvo.getPmvo().getAmount();
		String reason = rvo.getReason();

		
        // URL 인코딩을 적용한 POST 요청 바디 데이터
        String postData = 
                "imp_uid=" + imp_uid + 
		        "&amount=" + amount +
		        "&reason=" + reason;
        
        
     // API 엔드포인트 URL
        String apiUrl = "https://api.iamport.kr/payments/cancel";
        
        // access token, scope, 및 key를 URL에 포함하여 API 호출
        String apiEndpoint = apiUrl + "?access_token=";
       
        log.info(apiEndpoint);
		
		// HTTP GET 요청 보내기
		URL url = new URL(apiEndpoint);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Authorization", rvo.getAccess_token());
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
		int isOK = 0;
		if(responseCode == 200) {
			isOK = 1;
			isOK *= psv.refundSes(rvo.getUid());
		}
		
		return isOK;
	}
	
	
}
