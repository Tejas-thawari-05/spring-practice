package com.payloads;

import lombok.Data;

@Data
public class CommentDto {

	private int id;
	
	private String content;
	
	private UserDto user;
}
