package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payloads.CommentDto;
import com.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	//@PostMapping("/post/{id}/user/{userId}/comments")
	@PostMapping("/post/{id}/user/{userId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer id ,@PathVariable Integer userId){
		CommentDto comment = commentService.createComment(commentDto, id, userId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public String deleteComment(@PathVariable int commentId) {
		commentService.deleteComment(commentId);
		return "Comment Deleted successfully fo id :- "+commentId;
	}
}
