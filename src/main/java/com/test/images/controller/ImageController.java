/**

The ImageController class handles the endpoints related to the image upload, view, and delete operations.
*/
package com.test.images.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.images.service.ImageService;

@RestController
@RequestMapping("/users/images")
public class ImageController {

	private final ImageService imgurImageService;

	/**
	 * 
	 * Constructs a new ImageController instance with the given ImageService
	 * instance.
	 * 
	 * @param imgurImageService The ImageService instance for image-related
	 *                          operations.
	 */
	@Autowired
	public ImageController(final ImageService imgurImageService) {
		this.imgurImageService = imgurImageService;
	}

	/**
	 * 
	 * Uploads an image and associates it with the user specified by the userId
	 * parameter.
	 * 
	 * @param headers The request headers.
	 * @param userId  The ID of the user to associate the image with.
	 * @param file    The image file to upload.
	 * @return A ResponseEntity with the response body containing the uploaded image
	 *         data.
	 */
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestHeader Map<String, String> headers, @RequestParam Long userId,
			@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(imgurImageService.upload(file, userId));
	}

	/**
	 * 
	 * Retrieves the image data for the image with the specified imageId.
	 * 
	 * @param imageId The ID of the image to retrieve.
	 * @return A ResponseEntity with the response body containing the image data.
	 */
	@GetMapping("/{imageId}")
	public ResponseEntity<?> getImage(final @PathVariable String imageId) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(imgurImageService.view(imageId));
	}

	/**
	 * 
	 * Deletes the image with the specified imageId.
	 * 
	 * @param imageId The ID of the image to delete.
	 * @return A ResponseEntity with the response body containing the deleted image
	 *         data.
	 */
	@DeleteMapping("/{deleteHash}")
	public ResponseEntity<?> delete(final @PathVariable String deleteHash, @RequestParam Long userId) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(imgurImageService.delete(deleteHash,userId));
	}

}
