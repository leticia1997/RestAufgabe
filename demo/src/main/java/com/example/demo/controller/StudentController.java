package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Student;

@RestController
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		return studentService.create(student);
	}

	@PutMapping("/update/{id}")
	public Student update(@PathVariable int id, @RequestBody Student update) {

		Student response = new Student();
		Student student = studentService.update(id, update);

		// wenn wir einfach "student" zurück geben, gibt's ein Jackson Exception,
		// deshalb kopieren wir jedes Feld in Response
		response.setId(student.getId());
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setEmail(student.getEmail());

		System.out.println(student);

		return response;
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {

		studentService.delete(id);

	}

	@GetMapping("/{id}")
	public Student getOne(@PathVariable int id) {
		Student response = new Student();
		Student student = studentService.getFromDb(id);

		// wenn wir einfach "student" zurück geben, gibt's ein Jackson Exception,
		// deshalb kopieren wir jedes Feld in Response
		response.setId(student.getId());
		response.setFirstName(student.getFirstName());
		response.setLastName(student.getLastName());
		response.setEmail(student.getEmail());

		System.out.println(student);
		return response;
	}

}
