package com.test.images.service.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TokenHelper {

	@Value("${image.token.url.imgur}")
	private String TOKEN_URL;

	@Value("${image.token.imgur.client_id}")
	private String CLIENT_ID;

	@Value("${image.token.imgur.client_secret}")
	private String CLIENT_SECRET;

	@Value("${image.token.imgur.grant_type}")
	private String GRANT_TYPE;

	@Value("${image.token.imgur.refresh_token}")
	private String REFRESH_TOKEN;

	public String get() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("refresh_token", REFRESH_TOKEN);
		requestBody.add("client_id", CLIENT_ID);
		requestBody.add("client_secret", CLIENT_SECRET);
		requestBody.add("grant_type", GRANT_TYPE);

		HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

		ResponseEntity<String> response = new RestTemplate().postForEntity(TOKEN_URL.trim(), requestEntity,
				String.class);

		return getToken(response.getBody());

	}

	private String getToken(String tokenResponse) {

		ObjectMapper mapper = new ObjectMapper();
		String token = null;
		try {
			JsonNode jsonNode = mapper.readTree(tokenResponse);
			token = jsonNode.get("access_token").asText();
			System.out.println("Access Token: " + token);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return token;
	}

}
