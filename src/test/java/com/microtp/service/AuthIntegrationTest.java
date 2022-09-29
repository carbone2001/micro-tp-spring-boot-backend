package com.microtp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.microtp.dtos.auth.JwtResponse;
import com.microtp.dtos.auth.UserAuthCredentials;

@SpringBootTest
public class AuthIntegrationTest {
	
	@Value("${testing.base-url}")
	private String baseURL;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	void login() {
		UserAuthCredentials credentials = new UserAuthCredentials();
		credentials.setUsuario("admin");
		credentials.setClave("Asdasd123123!");
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<UserAuthCredentials> request = new HttpEntity<UserAuthCredentials>(credentials, headers);
		
		ResponseEntity<Object> response = this.restTemplate.exchange(
				this.baseURL+"api/auth", 
				HttpMethod.POST,
				request,
				Object.class);
		
		assertTrue(response != null && response.getStatusCode() == HttpStatus.OK);
	}
}
