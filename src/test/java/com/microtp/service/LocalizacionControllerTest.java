package com.microtp.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

public class LocalizacionControllerTest {
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	void createLocalizacion() {
		assertTrue(true);
	}
}
