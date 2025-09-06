package com.services;

import com.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto, Integer id, Integer userId);
	
	void deleteComment(Integer commentId);
}
