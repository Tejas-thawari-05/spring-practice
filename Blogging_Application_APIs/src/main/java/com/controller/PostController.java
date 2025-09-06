package com.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import com.config.AppConstant;
import com.payloads.PostDto;
import com.payloads.PostResponse;
import com.services.FileService;
import com.services.PostService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	@PostMapping("/user/{userId}/category/{categoryId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable int userId, @PathVariable int categoryId){
		
		PostDto post = postService.createPost(postDto, userId, categoryId);
		
		return new ResponseEntity<PostDto>(post,HttpStatus.CREATED);
	}
	
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto , @PathVariable int postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable int userId){
		List<PostDto> postByUser = postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postByUser,HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable int categoryId){
		List<PostDto> postByCategory = postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(postByCategory,HttpStatus.OK);
	}
	
	@GetMapping("/posts")    // http://localhost:8080/api/posts?pageNumber=0&pageSize=3&sortBy=id&sortDir=asc
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber" , defaultValue = AppConstant.PAGE_NUMBER , required = false) int pageNumber,
			@RequestParam(value = "pageSize" , defaultValue = AppConstant.PAGE_SIZE , required = false ) int pageSize ,
			@RequestParam(value = "sortBy" , defaultValue = AppConstant.SORT_BY , required = false) String sortBy ,
			@RequestParam(value = "sortDir" , defaultValue = AppConstant.SORT_DIR , required = false ) String sortDir	
	){
		
		PostResponse allPost = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getpostById(@PathVariable int postId){
		PostDto postById = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	public String deletePost(@PathVariable int postId) {
		postService.deletePost(postId);
		
		return "Post Deleted Successfully of id :- "+postId;
	}
	
	@GetMapping("/posts/search/{title}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String title){
		List<PostDto> searchPost = postService.searchPost(title);
		return new ResponseEntity<List<PostDto>>(searchPost,HttpStatus.OK);
	}
	
	// post image upload
	@PostMapping("/post/image/upload/{id}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable int id) throws IOException{
		
		PostDto postById = postService.getPostById(id);
		String fileName = fileService.uploadImage(path, image);
		
		postById.setImageName(fileName);
		
		PostDto updatePost = postService.updatePost(postById, id);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	// method to serve image
	@GetMapping(value = "/post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downlodeImage(@PathVariable("imageName") String imageName,
			HttpServletResponse response) throws IOException {
		InputStream resource = fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
}








