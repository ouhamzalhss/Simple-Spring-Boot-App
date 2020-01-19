package com.ouhamza.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ouhamza.demo.model.Student;

@RepositoryRestResource(collectionResourceRel = "studentss" , path = "studentss")
public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findByMarks(int marks);
	
	@Query("from Student where marks=?1 order by id")
	List<Student> findByMarksSorted(int marks);
}
