package com.ouhamza.demo.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ouhamza.demo.dao.StudentRepo;
import com.ouhamza.demo.model.Student;

@Controller
public class HomeController {

	@Autowired
	private StudentRepo studentRepo;
	
	
	//http://localhost:8080/home?name=ouhamza
//	@RequestMapping("home")
//	public String home(@RequestParam("name") String name, Model model) {
//	//public String home(String name, Model model) {		
//     	model.addAttribute("name", name);
//		return "home";
//	}
	
	
	//http://localhost:8080/home?name=ouhamza
//	@RequestMapping("home")
//	public ModelAndView home(@RequestParam("name") String name) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("name", name);
//     	mv.setViewName("home");
//		return mv;
//	}
	
	
	//http://localhost:8080/home?id=1&name=ouhamza&marks=18
	@RequestMapping("home")
	public ModelAndView home(Student std) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("student", std);
     	mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping("formStudent")
	public String formStudent() {
		return "addStudent";
	}
	
	@RequestMapping("addStudent")
	public String addStudent(Student student ) {
		this.studentRepo.save(student);
		return "addStudent";
	}
	@RequestMapping("getStudent")
	public String getStudent(Integer id, Model model) {
		//Student std = this.studentRepo.getOne(id); // return exception if no value to return
		
		System.out.println(studentRepo.findByMarks(10));
		System.out.println(studentRepo.findByMarksSorted(10));
		
		Student std = this.studentRepo.findById(id).orElse(new Student());
		model.addAttribute("student", std);
		return "addStudent";
	}
	
	
	@RequestMapping(path="students", produces = {"Application/xml"})
	@ResponseBody
	public List<Student> getStudents() {
		return studentRepo.findAll();
	}
	
    @PostMapping(path="students", consumes = {"Application/xml"})
	@ResponseBody
	public Student saveStudent(@RequestBody Student std) {
		return studentRepo.save(std);
	}
	
	
	@RequestMapping(path="students/{id}", produces = {"Application/json"})
	@ResponseBody
	public Optional<Student> getStudent(@PathVariable("id") int id) {
		return studentRepo.findById(id);
	}
	
	@DeleteMapping("students/{id}")
	@ResponseBody
	public String deleteStudent(@PathVariable("id") int id) {
		 Student std = studentRepo.getOne(id);
		 studentRepo.delete(std);
		 return "Deleted";
	}
	

	@PutMapping("students")
	@ResponseBody
	public Student updateOrSaveStudent(@RequestBody Student newStd) {
		 studentRepo.save(newStd);
		 return newStd;
	}
}
