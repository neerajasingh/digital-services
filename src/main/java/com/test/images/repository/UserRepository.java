/**

This interface represents the User Repository responsible for performing CRUD operations on User entity.
The JpaRepository interface provides the default implementation for database operations.
The @Repository annotation is used to indicate that this interface is a repository.
*/
package com.test.images.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.images.domainmodel.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

}
