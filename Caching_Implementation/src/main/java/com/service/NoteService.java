package com.service;

import java.time.Clock;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.entity.Note;
import com.repository.Note_Respository;

@Service
public class NoteService {

	@Autowired
	private Note_Respository noteRespository;
	
	
	public Note createNote(Note note) {
		note.setAddedData(LocalDate.now(Clock.systemUTC()));
		return noteRespository.save(note);
	}
	
	public List<Note> getAll(){
		return noteRespository.findAll();
	}
	
	@Cacheable(value = "notes" , key = "#noteId")
	public Note getById(int noteId) {
		return noteRespository.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));
	}
	
	@CachePut(value = "note" , key = "#noteId")
	public Note updateNote(int noteId, Note note) {
		Note throw1 = noteRespository.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));
		throw1.setTitle(note.getTitle());
		throw1.setContent(note.getContent());
		throw1.setLive(note.isLive());
		throw1.setAddedData(LocalDate.now(Clock.systemUTC()));
	
		return noteRespository.save(throw1);
	}
	
	@CacheEvict(value = "note" , key = "#noteId")
	public void delete(int noteId) {
		Note note = noteRespository.findById(noteId).orElseThrow(() -> new RuntimeException("Note with given id not found"));
		noteRespository.delete(note);
	}
} 
