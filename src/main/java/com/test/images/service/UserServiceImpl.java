/**

This class implements the UserService interface and provides the implementation for all its methods.
It is responsible for managing user data in the system.
@author [Author Name]
@version [Version Number]
@since [Date of Creation]
*/
package com.test.images.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.images.domainmodel.User;
import com.test.images.repository.ImageRepository;
import com.test.images.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final ImageRepository imageRepository;

	/**
	 * Constructor for the UserServiceImpl class.
	 * 
	 * @param userRepository  the repository used for managing user data.
	 * @param imageRepository the repository used for managing image data.
	 */

	@Autowired
	public UserServiceImpl(final UserRepository userRepository, final ImageRepository imageRepository) {
		this.userRepository = userRepository;
		this.imageRepository = imageRepository;
	}

	/**
	 * Saves the user entity in the database.
	 * 
	 * @param user the user entity to be saved.
	 * @return the saved user entity.
	 */

	@Override
	public User save(User user) {

		return userRepository.save(user);
	}

	/**
	 * Retrieves all user entities from the database.
	 * 
	 * @return a list of all user entities.
	 */

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	/**
	 * Retrieves a user entity with the given ID from the database.
	 * 
	 * @param userId the ID of the user entity to be retrieved.
	 * @return an optional user entity with the given ID.
	 */

	@Override
	public Optional<User> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	/**
	 * Deletes the images with the given IDs from the database.
	 * 
	 * @param imagesToDelete a list of IDs of the images to be deleted.
	 */

	@Override
	public void deleteImages(List<String> imagesToDelete) {
		imageRepository.deleteAllByDeleteHashIn(imagesToDelete);
	}
}
