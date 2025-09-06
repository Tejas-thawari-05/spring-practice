package com.payloads;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {

	private List<PostDto> content;
	
	private int pagenumber;
	
	private int pageSize;
	
	private long totalElement;
	
	private  int totalpages;
	
	private boolean lastPage;
	
}
