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

import com.entity.mongoDB.MongoDB;
import com.entity.mysql.mysql;
import com.mongoDB.repository.MongoDBRepository;
import com.mysql.repository.MySqlRepository;

@RestController
@RequestMapping("/api")
public class MultiDBController {

	@Autowired
	private MySqlRepository mySqlRepository;
	
	@Autowired
	private MongoDBRepository mongoDBRepository;
	
	@PostMapping("/mysql")
	public String saveStudentInMysql(@RequestBody mysql mySql) {
		mySqlRepository.save(mySql);
		return "Student detail saved...";
	}
	
	@PostMapping("/mongodb")
	public String saveStudentInMongoDB(@RequestBody MongoDB mongoDB) {
		mongoDBRepository.save(mongoDB);
		return "Student detail saved...";
	}
	
	@DeleteMapping("/mysql/{id}")
	public String delStudentFromMysql(@PathVariable int id) {
		mySqlRepository.deleteById(id);
		return "Student Deleted Successfully...";
	}
	
	@DeleteMapping("/mongodb/{id}")
	public String delStudentFromMongoDB(@PathVariable int id) {
		mongoDBRepository.deleteById(id);
		return "Student Deleted Successfully...";
	}
	
	@PutMapping("/mysql/{id}")
	public String updateStudent(@PathVariable int id, @RequestBody mysql mySql) {

		Optional<mysql> stdList = mySqlRepository.findById(id);
		if (stdList.isPresent()) {
			mysql existStd = stdList.get();
			existStd.setFname(mySql.getFname());
			existStd.setLname(mySql.getLname());
			existStd.setAddress(mySql.getAddress());
			existStd.setContact(mySql.getContact());

			mySqlRepository.save(existStd);
			return "Student Details Updated for " + id;
		}

		return "Student not Present of id - " + id;
	}
	
	@PutMapping("/mongodb/{id}")
	public String updateStudentInMongoDB(@PathVariable int id, @RequestBody MongoDB mongoDB) {

		Optional<MongoDB> stdList = mongoDBRepository.findById(id);
		if (stdList.isPresent()) {
			MongoDB existStd = stdList.get();
			existStd.setFname(mongoDB.getFname());
			existStd.setLname(mongoDB.getLname());
			existStd.setAddress(mongoDB.getAddress());
			existStd.setContact(mongoDB.getContact());

			mongoDBRepository.save(existStd);
			return "Student Details Updated for " + id;
		}

		return "Student not Present of id - " + id;
	}
	
	@GetMapping("/mysql/readAll")
	public ResponseEntity<List<mysql>> readAllFromMySql(){
		List<mysql> stdList = new ArrayList<>();
		mySqlRepository.findAll().forEach(stdList :: add);
		
		return new ResponseEntity<List<mysql>>(stdList,HttpStatus.OK);
	}
	
	@GetMapping("/mongodb/readAll")
	public ResponseEntity<List<MongoDB>> readAllFromMongoDB(){
		List<MongoDB> stdList = new ArrayList<>();
		mongoDBRepository.findAll().forEach(stdList :: add);
		
		return new ResponseEntity<List<MongoDB>>(stdList,HttpStatus.OK);
	}
}
