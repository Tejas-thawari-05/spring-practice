package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.entity.Student;
import com.repository.StudentRepository;



@RestController
@RequestMapping("/std")
public class StudentController {

	@Autowired
	StudentRepository studentRepository;

	@PostMapping("/student")
	public String addStudent(@RequestBody Student std) {
		studentRepository.save(std);
		return "Student Added Successfully....";
	}

	@DeleteMapping("/student/{id}")
	public String delStudent(@PathVariable int id) {
		studentRepository.deleteById(id);
		return "Student Deleted Successfully...";
	}

	@PutMapping("/student/{id}")
	public String updateStudent(@PathVariable int id, @RequestBody Student std) {

		Optional<Student> stdList = studentRepository.findById(id);
		if (stdList.isPresent()) {
			Student existStd = stdList.get();
			existStd.setFname(std.getFname());
			existStd.setLname(std.getLname());
			existStd.setRollNo(std.getRollNo());
			existStd.setSection(std.getSection());
			existStd.setContact(std.getContact());

			studentRepository.save(existStd);
			return "Student Details Updated for " + id;
		}

		return "Student not Present of id - " + id;
	}

	@GetMapping("/student/{section}")
	public List<Student> getStudentsBySection(@PathVariable String section) {
		return studentRepository.findBySectionIgnoreCase(section);
	}
	
	@GetMapping("/student/byid/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		Optional<Student> std = studentRepository.findById(id);
		if(std.isPresent()) {
			return new ResponseEntity<Student>(std.get(),HttpStatus.FOUND);
		}
		return new ResponseEntity<Student>(std.get(),HttpStatus.NOT_FOUND);
	}
	

	
	@GetMapping("/student/readAll")
	public ResponseEntity<List<Student>> readAll(){
		List<Student> stdList = new ArrayList<>();
		studentRepository.findAll().forEach(stdList :: add);
		
		return new ResponseEntity<List<Student>>(stdList,HttpStatus.OK);
	}
	
	@DeleteMapping("/student/delAll")
	public String delAllStudent() {
		studentRepository.deleteAll();
		return "All Student Deleted Successfully...";
	}

}
