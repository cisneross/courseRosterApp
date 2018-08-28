package com.sergio.exam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sergio.exam.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
	List<Course> findAll();
	Optional<Course> findById(Long id);
}
