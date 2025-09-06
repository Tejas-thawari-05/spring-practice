package com.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.entity.Book;
import com.repository.BookRepository;
import com.service.BookService;

import lombok.Getter;
import lombok.Setter;

@Controller

public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	@MutationMapping("createBook")
	public Book addBook(@Argument BookInput book){
		Book b = new Book();
		b.setTitle(book.getTitle());
		b.setDescription(book.getDescription());
		b.setAuthor(book.getAuthor());
		b.setPrice(book.getPrice());
		b.setPages(book.getPages());
		
		return bookService.addBook(b);
	}
	
	@QueryMapping("getAllBooks")
	public List<Book> getAllBooks(){
		List<Book> allBooks = bookService.getAllBooks();
		return allBooks;
	}
	
	@QueryMapping("getBookById")
	public Book getBookById(@Argument int id){
		return bookService.getBookById(id);
	}
	
	
	@DeleteMapping("/{id}")
	public String delBook(@PathVariable int id) {
		bookRepository.deleteById(id);
		
		return "Book deleted successfully";
	}
	
}

@Getter
@Setter
class BookInput{
	
private String title;
	
	private String description;
	
	private String author;
	
	private double price;
	
	private int pages;
}
