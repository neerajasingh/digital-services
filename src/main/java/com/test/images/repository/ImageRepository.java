/**

The ImageRepository interface is responsible for providing CRUD operations and custom queries for the Image entity.
It extends the JpaRepository interface, which provides all the basic CRUD operations out of the box.
*/
package com.test.images.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.images.domainmodel.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
	/**
	 * Returns a list of all the Images that belong to a specific user identified by
	 * the given userId.
	 * 
	 * @param userId The ID of the user whose images are to be returned
	 * @return A list of Images that belong to the specified user
	 */
	List<Image> findByUserId(Long userId);

	/**
	 * Deletes all the Images that have IDs that match the IDs provided in the ids
	 * list.
	 * 
	 * @param ids A list of IDs of Images that are to be deleted
	 */
	public void deleteAllByIdIn(List<Long> ids);

}
