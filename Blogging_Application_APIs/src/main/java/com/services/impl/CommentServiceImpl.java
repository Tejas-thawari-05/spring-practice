package com.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Comment;
import com.entity.Post;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.payloads.CommentDto;
import com.repository.CommentRepository;
import com.repository.Post_Repository;
import com.repository.User_Repository;
import com.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private Post_Repository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private User_Repository userRepository;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer id,Integer userId) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", id));
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		Comment comment = modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		comment.setUser(user);
		
		Comment saveComment = commentRepository.save(comment);
		
		return modelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment Id", commentId));
		
		commentRepository.delete(comment);
	}

}
