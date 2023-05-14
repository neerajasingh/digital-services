/**

This is the implementation class for ImageService. It provides methods to upload,
delete and view images using Imgur API.
@author [your name]
@version 1.0
@since [date]
*/
package com.test.images.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.test.images.service.helper.ImageDeleteHelper;
import com.test.images.service.helper.ImageUploadHelper;
import com.test.images.service.helper.ImageViewHelper;
import com.test.images.service.helper.UserUpdateHelper;

@Service("imgurImageService")
public class ImgurImageService implements ImageService {

	private final ImageUploadHelper imageUploadHelper;
	private final ImageDeleteHelper imageDeleteHelper;
	private final ImageViewHelper imageViewHelper;
	private final UserUpdateHelper userUpdateHelper;

	/**
	 * Constructor to initialize ImageUploadHelper, ImageDeleteHelper and
	 * ImageViewHelper objects using dependency injection.
	 * 
	 * @param imageUploadHelper The helper class for uploading images to Imgur API.
	 * @param imageDeleteHelper The helper class for deleting images from Imgur API.
	 * @param imageViewHelper   The helper class for viewing images from Imgur API.
	 * @param userUpdateHelper  The helper class for updating given user for image
	 *                          association.
	 */
	@Autowired
	public ImgurImageService(ImageUploadHelper imageUploadHelper, ImageDeleteHelper imageDeleteHelper,
			ImageViewHelper imageViewHelper, final UserUpdateHelper userUpdateHelper) {
		super();
		this.imageUploadHelper = imageUploadHelper;
		this.imageDeleteHelper = imageDeleteHelper;
		this.imageViewHelper = imageViewHelper;
		this.userUpdateHelper = userUpdateHelper;
	}

	/**
	 * Deletes an image from Imgur API.
	 * 
	 * @param imageId The ID of the image to be deleted.
	 * @return A string representation of the response body from Imgur API.
	 */
	@Override
	public String delete(String deleteHash, Long userId) {
		String response = imageDeleteHelper.delete(deleteHash.trim()).getBody();
		userUpdateHelper.deleteImage(deleteHash, userId);
		return response;
	}

	/**
	 * Uploads an image to Imgur API.
	 * 
	 * @param file The image file to be uploaded.
	 * @return A string representation of the response body from Imgur API.
	 */
	@Override
	public String upload(MultipartFile file, Long userId) {

		String response = imageUploadHelper.upload(file).getBody();
		userUpdateHelper.addImage(response, userId);

		return response;
	}

	/**
	 * Views an image from Imgur API.
	 * 
	 * @param imageId The ID of the image to be viewed.
	 * @return A string representation of the response body from Imgur API.
	 */
	@Override
	public String view(String imageId) {
		return imageViewHelper.view(imageId.trim()).getBody();
	}
}
