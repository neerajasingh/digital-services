/**

This class provides helper methods to retrieve an image from a remote server using the Imgur API.
*/

package com.test.images.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.test.images.config.Constants;

@Component
public class ImageViewHelper {
	@Value("${image.get.url.imgur}")
	private String IMAGE_VIEW_URL;

	@Value("${image.token.imgur.client_id}")
	private String CLIENT_ID;

	private final RestTemplate imageRestTemplate;

	/**
	 * Constructor for creating a new instance of ImageViewHelper.
	 * 
	 * @param imageRestTemplate the RestTemplate used to make HTTP requests to the
	 *                          Imgur API
	 */
	@Autowired
	public ImageViewHelper(final RestTemplate imageRestTemplate) {
		this.imageRestTemplate = imageRestTemplate;
	}

	/**
	 * Retrieves an image from the Imgur API given an image ID.
	 * 
	 * @param imageId the ID of the image to retrieve
	 * @return a ResponseEntity object containing the response body of the HTTP
	 *         request made to the Imgur API
	 */
	public ResponseEntity<String> view(final String imageId) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.set(Constants.AUTHORIZATION, Constants.CLIENT_ID_KEY + CLIENT_ID);
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return imageRestTemplate.exchange(IMAGE_VIEW_URL + imageId, HttpMethod.GET, entity, String.class, headers);

	}
}
