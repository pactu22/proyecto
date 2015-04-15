/*
 * Created on 14 Apr 2015 ( Time 18:45:46 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.stories.business.service.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import org.stories.bean.Comment;
import org.stories.bean.jpa.CommentEntity;
import org.stories.bean.jpa.UsertableEntity;
import org.stories.bean.jpa.SubmissionEntity;

/**
 * Mapping between entity beans and display beans.
 */
@Component
public class CommentServiceMapper extends AbstractServiceMapper {

	/**
	 * ModelMapper : bean to bean mapping library.
	 */
	private ModelMapper modelMapper;
	
	/**
	 * Constructor.
	 */
	public CommentServiceMapper() {
		modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Mapping from 'CommentEntity' to 'Comment'
	 * @param commentEntity
	 */
	public Comment mapCommentEntityToComment(CommentEntity commentEntity) {
		if(commentEntity == null) {
			return null;
		}

		//--- Generic mapping 
		Comment comment = map(commentEntity, Comment.class);

		//--- Link mapping ( link to Usertable )
		if(commentEntity.getUsertable() != null) {
			comment.setBy(commentEntity.getUsertable().getId());
		}
		//--- Link mapping ( link to Submission )
		if(commentEntity.getSubmission() != null) {
			comment.setParent(commentEntity.getSubmission().getId());
		}
		return comment;
	}
	
	/**
	 * Mapping from 'Comment' to 'CommentEntity'
	 * @param comment
	 * @param commentEntity
	 */
	public void mapCommentToCommentEntity(Comment comment, CommentEntity commentEntity) {
		if(comment == null) {
			return;
		}

		//--- Generic mapping 
		map(comment, commentEntity);

		//--- Link mapping ( link : comment )
		if( hasLinkToUsertable(comment) ) {
			UsertableEntity usertable1 = new UsertableEntity();
			usertable1.setId( comment.getBy() );
			commentEntity.setUsertable( usertable1 );
		} else {
			commentEntity.setUsertable( null );
		}

		//--- Link mapping ( link : comment )
		if( hasLinkToSubmission(comment) ) {
			SubmissionEntity submission2 = new SubmissionEntity();
			submission2.setId( comment.getParent() );
			commentEntity.setSubmission( submission2 );
		} else {
			commentEntity.setSubmission( null );
		}

	}
	
	/**
	 * Verify that Usertable id is valid.
	 * @param Usertable Usertable
	 * @return boolean
	 */
	private boolean hasLinkToUsertable(Comment comment) {
		if(comment.getBy() != null) {
			return true;
		}
		return false;
	}

	/**
	 * Verify that Submission id is valid.
	 * @param Submission Submission
	 * @return boolean
	 */
	private boolean hasLinkToSubmission(Comment comment) {
		if(comment.getParent() != null) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ModelMapper getModelMapper() {
		return modelMapper;
	}

	protected void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

}