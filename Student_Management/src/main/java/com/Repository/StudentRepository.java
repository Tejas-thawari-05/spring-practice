package com.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	Optional<Student> findBySection(String section);

	List<Student> findBySectionIgnoreCase(String section);

}
