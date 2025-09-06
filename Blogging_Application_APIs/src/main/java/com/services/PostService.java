package com.services;

import java.util.List;

import com.payloads.PostDto;
import com.payloads.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postDto, int categoryId, int userId);
	
	PostDto updatePost(PostDto postDto, int id);
	
	void deletePost(int id);
	
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(int id);
	
	List<PostDto> getPostByCategory(int categoryId);
	
	List<PostDto> getPostByUser(int userId);
	
	List<PostDto> searchPost(String title);
}
