package it.partec.webappspringsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.partec.webappspringsecurity.dto.StudentDto;
import it.partec.webappspringsecurity.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping
	public ResponseEntity<List<StudentDto>> getListStudent(Authentication authentication) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<StudentDto> studentList = null;
		studentList = studentService.getListStudent();
		return new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping public ResponseEntity<List<StudentDto>> getSearchStudent(
	 * 
	 * @RequestParam(name = "name", required = false) String name,
	 * 
	 * @RequestParam(name = "surname", required = false) String surname,
	 * 
	 * @RequestParam(name = "age", required = false) Long age,
	 * 
	 * @RequestParam(name = "className", required = false) String className) {
	 * StudentDto studentDto = new StudentDto(); studentDto.setName(name);
	 * studentDto.setSurname(surname); studentDto.setAge(age);
	 * studentDto.setClassName(className); List<StudentDto> studentList = null; try
	 * { studentList = studentService.searchStudent(studentDto); } catch(Exception
	 * e) { return new
	 * ResponseEntity<List<StudentDto>>(HttpStatus.INTERNAL_SERVER_ERROR); } return
	 * new ResponseEntity<List<StudentDto>>(studentList, HttpStatus.OK); }
	 */
	
	@PreAuthorize("hasAuthority('ROLE_USER')")
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getStudent(Authentication authentication, @PathVariable("id") long id) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		StudentDto studentDto = null;
		studentDto = studentService.getStudent(id);
		return new ResponseEntity<StudentDto>(studentDto, HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<Object> addStudent(Authentication authentication, @RequestBody StudentDto studentDto) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		studentService.addStudent(studentDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteStudent(Authentication authentication, @PathVariable("id") long id) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PutMapping
	public ResponseEntity<Object> updateStudent(Authentication authentication, @PathVariable("id") long id, @RequestBody StudentDto studentDto) throws Exception {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		studentService.updateStudent(id, studentDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
