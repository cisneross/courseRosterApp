package com.sergio.exam.service;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.sergio.exam.models.Course;
import com.sergio.exam.models.User;
import com.sergio.exam.models.UserCourse;
import com.sergio.exam.repository.CourseRepository;
import com.sergio.exam.repository.UserCourseRepository;
import com.sergio.exam.repository.UserRepository;

@Service
public class CourseService {
	private final UserRepository uR;
	private final CourseRepository cR;
	private final UserCourseRepository uCR;
	public CourseService(UserRepository uR,CourseRepository cR,UserCourseRepository uCR) {
		this.uR = uR;
		this.cR = cR;
		this.uCR = uCR;
	}
	// register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return uR.save(user);
    }
 // find user by email
    public User findByEmail(String email) {
        return uR.findByEmail(email);
    }
 // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = uR.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	} 
    }
    //find course by id
    public List<Object[]> findSignUpsById(Long id) {	 
    	List<Object[]> signUps= uCR.findSignUps(id);
    	System.out.println(signUps);
    	return signUps;
    }
    public Course findCourseById(long id) {
    	Optional<Course> c = cR.findById(id);
    	
    	if(c.isPresent()) {
            return c.get();
    	} else {
    	    return null;
    	} 
    }
    //add a course
    public void addACourse(Long userId, Long courseId) {
    	//find user and course with id
    	Optional<User> u = uR.findById(userId);
    	Optional<Course> c = cR.findById(courseId);
    	//put them in a variable 
    	User uObj = u.get();
    	Course cObj = c.get();
    	//save the new userCourse
    	UserCourse userCourse = new UserCourse(uObj,cObj);
    	uCR.save(userCourse);
    }
    //update 
    public void update(Course course) {
    	cR.save(course);
 
    }
    //delete course
    public void deleteCourseById(Long id) {
    	cR.deleteById(id);
    }
    
    
    public List<Course> findALLCourses() {
    	return cR.findAll();
    }
    //authenticate user
 // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = uR.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    //createCourse
    public Course createACourse(Course course) {
    	return cR.save(course);
    }
    
    
}
