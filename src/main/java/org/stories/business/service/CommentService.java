/*
 * Created on 14 Apr 2015 ( Time 18:45:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.stories.business.service;

import java.util.List;

import org.stories.bean.Comment;

/**
 * Business Service Interface for entity Comment.
 */
public interface CommentService { 

	/**
	 * Loads an entity from the database using its Primary Key
	 * @param id
	 * @return entity
	 */
	Comment findById( Integer id  ) ;

	/**
	 * Loads all entities.
	 * @return all entities
	 */
	List<Comment> findAll();

	/**
	 * Saves the given entity in the database (create or update)
	 * @param entity
	 * @return entity
	 */
	Comment save(Comment entity);

	/**
	 * Updates the given entity in the database
	 * @param entity
	 * @return
	 */
	Comment update(Comment entity);

	/**
	 * Creates the given entity in the database
	 * @param entity
	 * @return
	 */
	Comment create(Comment entity);

	/**
	 * Deletes an entity using its Primary Key
	 * @param id
	 */
	void delete( Integer id );


}
