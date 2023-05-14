/**

This class provides helper methods for deleting images from Imgur.
It uses the Imgur API to send HTTP DELETE requests for deleting images.
*/
package com.test.images.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.images.config.Constants;

@Component
public class ImageDeleteHelper {
	/**
	 * The URL for the Imgur image delete API endpoint.
	 */
	@Value("${image.delete.url.imgur}")
	private String IMAGE_DELETE_URL;

	/**
	 * The client ID for accessing the Imgur API.
	 */
	@Value("${image.token.imgur.client_id}")
	private String CLIENT_ID;

	/**
	 * The TokenHelper instance used for retrieving authorization tokens.
	 */
	private final TokenHelper tokenHelper;

	/**
	 * The RestTemplate instance used for making HTTP requests.
	 */
	private final RestTemplate imageRestTemplate;

	/**
	 * Constructs a new instance of ImageDeleteHelper.
	 * 
	 * @param tokenHelper       The TokenHelper instance used for retrieving
	 *                          authorization tokens.
	 * @param imageRestTemplate The RestTemplate instance used for making HTTP
	 *                          requests.
	 */
	@Autowired
	public ImageDeleteHelper(final TokenHelper tokenHelper, final RestTemplate imageRestTemplate) {
		this.tokenHelper = tokenHelper;
		this.imageRestTemplate = imageRestTemplate;
	}

	/**
	 * Deletes the image with the specified ID from Imgur.
	 * 
	 * @param imageId The ID of the image to delete.
	 * @return A ResponseEntity containing the response body of the HTTP DELETE
	 *         request.
	 */
	public ResponseEntity<String> delete(final String imageId) {

		HttpHeaders headers = new HttpHeaders();
		headers.set(Constants.AUTHORIZATION, Constants.BEARER + tokenHelper.get());

		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return imageRestTemplate.exchange(IMAGE_DELETE_URL + imageId, HttpMethod.DELETE, entity, String.class, headers);
	}
}
