package com.test.images.service;

import java.util.List;
import java.util.Optional;

import com.test.images.domainmodel.User;

public interface UserService {

	User save(User user);

	List<User> findAll();

	Optional<User> getUserById(Long userId);

	void deleteImages(List<String> imagesToDelete);
}
