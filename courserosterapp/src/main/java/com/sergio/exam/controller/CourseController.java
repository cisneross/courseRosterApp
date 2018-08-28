package com.sergio.exam.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sergio.exam.models.Course;
import com.sergio.exam.models.User;
import com.sergio.exam.service.CourseService;
import com.sergio.exam.validator.UserValidator;

@Controller
public class CourseController {
	private final UserValidator uV;
	private final CourseService cS;
	public CourseController(UserValidator uV, CourseService cS) {
		this.uV = uV;
		this.cS = cS;
	}
	@RequestMapping("/")
	public String registerLogin(@ModelAttribute("user") User user) {
		return "exam/reglog.jsp";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		uV.validate(user, result);
		if(result.hasErrors()) {
			return "exam/reglog.jsp"; 
		}
		else {
			cS.registerUser(user);
			session.setAttribute("id", user.getId());
    		return "redirect:/courses";
		}
	}
	//login
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(cS.authenticateUser(email, password)) {
    		User user = cS.findByEmail(email);
    		session.setAttribute("id", user.getId());
    		return "redirect:/courses";
    	}
    	else {
    		model.addAttribute("user", new User());
    		model.addAttribute("errorl", "Invalid email or password");
    		return "exam/reglog.jsp";
    	}
	}
	
	@RequestMapping("/courses")
	public String courseDashboard(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("id");
		if(id == null) {
			return "redirect:/";
		}
		else {
			User user = cS.findUserById(id);
			model.addAttribute("user", user);
			model.addAttribute("courses", cS.findALLCourses());
			return "exam/coursedash.jsp";
		}
		
	}
	@RequestMapping("/courses/new")
	public String course(@ModelAttribute("courseObj") Course course) {
		return "/exam/createcourse.jsp";
	}
	@RequestMapping(value="/courses/new", method=RequestMethod.POST)
	public String createCourse(@Valid @ModelAttribute("courseObj") Course course, BindingResult result,HttpSession session) {
		Long id = (Long) session.getAttribute("id");
		if(id == null) {
			return "redirect:/";
		}
		if (result.hasErrors()){
			return "exam/createcourse.jsp";
		}
		else {
			cS.createACourse(course);
			return "redirect:/courses/new";
		}
	}
	@RequestMapping("/courses/{id}/view")
	public String view(@PathVariable("id")Long courseId, HttpSession session,Model model) {
		Long id = (Long) session.getAttribute("id");
		if(id == null) {
			return "redirect:/";
		}
		else {
			model.addAttribute("course", cS.findCourseById(courseId) );
			model.addAttribute("signups", cS.findSignUpsById(courseId));
			return "exam/view.jsp";	
		}
	}

	//add a course
	@RequestMapping("/addcourse/{id}")
	public String addCourse(@PathVariable("id") Long courseId, HttpSession session) {
		Long userId = (Long) session.getAttribute("id");
		if(userId == null) {
			return "redirect:/";
		}
		else {
			cS.addACourse(userId, courseId);
			return "redirect:/courses";	
		}
	}
	//loads edit page
	@RequestMapping("/courses/{id}/edit")
	public String edit(@PathVariable("id")Long courseId, @ModelAttribute("editCourse") Course course, Model model) {
		model.addAttribute("course",cS.findCourseById(courseId));
		return "exam/edit.jsp";
	}
	//edit post 
	@RequestMapping(value = "/courses/{id}/edit", method=RequestMethod.POST)
	public String editProcess(@Valid @PathVariable("id") Long courseId, @ModelAttribute("editCourse") Course course, BindingResult result) {
		cS.update(course);
		return "redirect:/courses";
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		cS.deleteCourseById(id);
		return "redirect:/courses";
	}
	
	//logout
	@RequestMapping("/logout")
	public String logOut(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}
		
}
