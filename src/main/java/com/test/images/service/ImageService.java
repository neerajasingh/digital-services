package com.test.images.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
	public Object upload(MultipartFile file, Long userId);

	public String delete(String deletHash, Long userId);

	String view(final String imageId);

}
