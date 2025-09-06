package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Note;
import com.service.CacheInspectionService;
import com.service.NoteService;

@RestController
@RequestMapping("/api/v1")
public class Controller {

	@Autowired
	private NoteService noteService;
	
	@Autowired
	private CacheInspectionService cacheInspectionService;
	
	@PostMapping("/")
	private ResponseEntity<Note> create(@RequestBody Note note){
		return new ResponseEntity<Note>(noteService.createNote(note),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Note>> getAllNotes(){
		return ResponseEntity.ok(noteService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable int id){
		return ResponseEntity.ok(noteService.getById(id));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable int id, @RequestBody Note note){
		return ResponseEntity.ok(noteService.updateNote(id, note));
	}
	
	@DeleteMapping("/{id}")
	public String deleteNote(@PathVariable int id) {
		noteService.delete(id);
		return "Note Deleted Successfully of id :- "+id;
	}
	
	@GetMapping("/cacheData")
	public String getCacheData(){
		 return cacheInspectionService.printCacheContent("note");
	}
	
}
