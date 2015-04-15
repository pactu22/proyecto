/*
 * Created on 14 Apr 2015 ( Time 18:41:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package org.stories.rest.controller;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.stories.bean.Comment;
import org.stories.business.service.CommentService;
import org.stories.web.listitem.CommentListItem;

/**
 * Spring MVC controller for 'Comment' management.
 */
@Controller
public class CommentRestController {

	@Resource
	private CommentService commentService;
	
	@RequestMapping( value="/items/comment",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<CommentListItem> findAllAsListItems() {
		List<Comment> list = commentService.findAll();
		List<CommentListItem> items = new LinkedList<CommentListItem>();
		for ( Comment comment : list ) {
			items.add(new CommentListItem( comment ) );
		}
		return items;
	}
	
	@RequestMapping( value="/comment",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<Comment> findAll() {
		return commentService.findAll();
	}

	@RequestMapping( value="/comment/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Comment findOne(@PathVariable("id") Integer id) {
		return commentService.findById(id);
	}
	
	@RequestMapping( value="/comment",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Comment create(@RequestBody Comment comment) {
		return commentService.create(comment);
	}

	@RequestMapping( value="/comment/{id}",
			method = RequestMethod.PUT,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Comment update(@PathVariable("id") Integer id, @RequestBody Comment comment) {
		return commentService.update(comment);
	}

	@RequestMapping( value="/comment/{id}",
			method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void delete(@PathVariable("id") Integer id) {
		commentService.delete(id);
	}
	
}
