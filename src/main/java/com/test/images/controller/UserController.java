/**

This class represents the REST controller for managing user-related operations.
*/
package com.test.images.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.images.domainmodel.User;
import com.test.images.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	/**
	 * 
	 * Constructs a new UserController instance with the given UserService instance.
	 * 
	 * @param userService - the UserService instance to use for handling
	 *                    user-related operations.
	 */
	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * Registers a new user in the system.
	 * 
	 * @param user - the user to register.
	 * @return a ResponseEntity containing the registered user's information if
	 *         successful, or an error message if not.
	 */
	@PostMapping("/register")
	public ResponseEntity<User> register(@Valid @RequestBody User user) {
		return ResponseEntity.ok(userService.save(user));
	}

	/**
	 * 
	 * Retrieves all users from the system.
	 * 
	 * @return a ResponseEntity containing a list of all registered users if
	 *         successful, or an error message if not.
	 */
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		return ResponseEntity.ok(userService.findAll());
	}

	/**
	 * 
	 * Retrieves a user by their ID.
	 * 
	 * @param userId - the ID of the user to retrieve.
	 * @return a ResponseEntity containing the user's information if successful, or
	 *         an error message if not.
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "userId") Long userId) {

		Optional<User> optionalUser = userService.getUserById(userId);
		if (optionalUser.isPresent()) {
			return ResponseEntity.ok(optionalUser.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
