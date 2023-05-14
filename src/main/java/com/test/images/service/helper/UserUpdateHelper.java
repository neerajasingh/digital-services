package com.test.images.service.helper;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.images.domainmodel.Image;
import com.test.images.domainmodel.User;
import com.test.images.error.ServiceException;
import com.test.images.exception.handler.ErrorCodes;
import com.test.images.repository.ImageRepository;
import com.test.images.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserUpdateHelper {

	private final UserRepository userRepository;
	private final ImageRepository imageRepository;

	@Autowired
	public UserUpdateHelper(final UserRepository userRepository, final ImageRepository imageRepository) {
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	@SuppressWarnings("rawtypes")
	public void addImage(final String response, final Long userId) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map imgurResponse = objectMapper.readValue(response, Map.class);
			String jsonStr = imgurResponse.get("data").toString();
			String strWithoutBraces = jsonStr.substring(1, jsonStr.length() - 1);

			String[] pairs = strWithoutBraces.split(", ");
			List<String> imageAttributes = Arrays
					.asList(pairs).stream().filter(val -> val.contains("id") || val.contains("title")
							|| val.contains("description") || val.contains("type") || val.contains("deletehash"))
					.collect(Collectors.toList());
			Map<String, String> map = imageAttributes.stream().map(s -> s.split("="))
					.collect(Collectors.toMap(a -> a[0], a -> a[1]));
			User user = userRepository.findById(userId).get();

			Image image = new Image();
			image.setDescription(map.get("description"));
			image.setName(map.get("name"));
			image.setPartnerImageId(map.get("id"));
			image.setTitle(map.get("title"));
			image.setType(map.get("type"));
			image.setDeleteHash(map.get("deletehash"));
			image.setUser(user);

			imageRepository.save(image);

		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

	public void deleteImage(final String deleteHash, final Long userId) {

		Optional<User> optionalUser = userRepository.findById(userId);
		User user = null;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			List<Image> images = user.getImages().stream().filter(img -> !img.getDeleteHash().equals(deleteHash))
					.collect(Collectors.toList());
			user.setImages(images);
		} else {
			throw new ServiceException(ErrorCodes.NOT_FOUND, "No user found for userId " + deleteHash,
					HttpStatusCode.valueOf(404));
		}

		userRepository.saveAndFlush(user);

	}

}
