package com.sergio.exam.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sergio.exam.models.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository<UserCourse, Long> {
	Optional<UserCourse> findById(Long id);
	//user.name, usercourse.createdat, 
	@Query(value="SELECT users.name AS user_name, users_courses.created_at, courses.name AS courses_name FROM users JOIN users_courses ON users.id = users_courses.user_id JOIN courses ON courses.id = users_courses.course_id Where courses.id = ?1 ", nativeQuery=true)
	List<Object[]> findSignUps(Long id);
}
 