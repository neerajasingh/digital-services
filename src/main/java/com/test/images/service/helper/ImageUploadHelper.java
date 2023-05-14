/**

The ImageUploadHelper class provides helper methods to upload images using
the Imgur API.
*/
package com.test.images.service.helper;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.test.images.config.Constants;
import com.test.images.error.ServiceException;
import com.test.images.exception.handler.ErrorCodes;

@Component
public class ImageUploadHelper {

	@Autowired
	private RestTemplate imageRestTemplate;

	@Value("${image.upload.url.imgur}")
	private String IMAGE_UPLOAD_URL;

	@Value("${image.token.imgur.client_id}")
	private String CLIENT_ID;

	/**
	 * Uploads an image file to the Imgur API.
	 * 
	 * @param file the image file to upload
	 * @return a ResponseEntity containing the response body from the Imgur API
	 */
	public ResponseEntity<String> upload(MultipartFile file) {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.AUTHORIZATION, Constants.CLIENT_ID_KEY + CLIENT_ID);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		try {
			body.add(Constants.IMAGE_KEY, Base64.getEncoder().encodeToString(file.getBytes()));

		} catch (IOException e) {
			throw new ServiceException(ErrorCodes.INTERNAL_SERVER_ERROR, e.getMessage(), HttpStatusCode.valueOf(500));
		}
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		ResponseEntity<String> response = imageRestTemplate.exchange(IMAGE_UPLOAD_URL, HttpMethod.POST, requestEntity,
				String.class);
		return response;
	}

}
