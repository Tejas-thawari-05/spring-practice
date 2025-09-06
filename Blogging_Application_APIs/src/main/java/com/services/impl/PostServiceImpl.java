package com.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Category;
import com.entity.Post;
import com.entity.User;
import com.exceptions.ResourceNotFoundException;
import com.payloads.PostDto;
import com.payloads.PostResponse;
import com.repository.Category_Repository;
import com.repository.Post_Repository;
import com.repository.User_Repository;
import com.services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private Post_Repository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private User_Repository userRepository;
	
	@Autowired
	private Category_Repository categoryRepository;
	
	@Override
	public PostDto createPost(PostDto postDto,int categoryId, int userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId)); 
		
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepository.save(post);
		
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post save = postRepository.save(post);
		return modelMapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(int id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
		postRepository.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy,String sortDir) {
		
								//  Sorting by ascending and Descending order
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		}else {
			sort = Sort.by(sortBy).descending();
		}
		
									// pagination
		
		PageRequest p = PageRequest.of(pageNumber, pageSize, sort);
		
		Page<Post> pagePost = postRepository.findAll(p);
		
		List<Post> allPost = pagePost.getContent();
		
		List<PostDto> collect = allPost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(collect);
		postResponse.setPagenumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	
	@Override
	public PostDto getPostById(int id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", id));
		
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Ctaegory Id", categoryId));
		
		List<Post> listpost = postRepository.findByCategory(category);
		
		List<PostDto> collect = listpost.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(int userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User Id", userId));
		
		List<Post> byUser = postRepository.findByUser(user);
		
		List<PostDto> collect = byUser.stream().map((user1) -> modelMapper.map(user1, PostDto.class)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> byTitleContaining = postRepository.findByTitleContaining(keyword);
		List<PostDto> collect = byTitleContaining.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	

}
