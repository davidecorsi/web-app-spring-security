package it.partec.webappspringsecurity.utils;

import it.partec.webappspringsecurity.dto.StudentDto;

public class StudentUtils {
	
	public static StudentDto getOneStudent() {
		StudentDto student = new StudentDto();
		student.setName("Antonio");
		student.setSurname("Frattasi");
		student.setAge(22L);
		student.setClassName("2A");
		return student;
	}
}
