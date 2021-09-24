package it.partec.webappspringsecurity.service;

import java.util.List;

import it.partec.webappspringsecurity.dto.StudentDto;

public interface StudentService {

	public List<StudentDto> getListStudent() throws Exception;
	public StudentDto getStudent(long id) throws Exception;
	public void addStudent(StudentDto studentDto) throws Exception;
	public void deleteStudent(long id) throws Exception;
	public List<StudentDto> searchStudent(StudentDto studentDto) throws Exception;
	public void updateStudent(long id, StudentDto studentDto) throws Exception;
}