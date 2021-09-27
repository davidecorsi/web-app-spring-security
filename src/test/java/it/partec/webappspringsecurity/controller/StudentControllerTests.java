package it.partec.webappspringsecurity.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.partec.webappspringsecurity.dto.StudentDto;
import it.partec.webappspringsecurity.mockuser.MockUser;
import it.partec.webappspringsecurity.service.StudentService;
import it.partec.webappspringsecurity.utils.StudentUtils;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private StudentService studentService;

	@WithMockUser(username = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
	@Test
	void addStudentCreatedTest() throws Exception {
		StudentDto studentDto = StudentUtils.getOneStudent();
		mockMvc.perform(post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(studentDto)))
		.andExpect(status().isCreated());
	}

	@MockUser
	@Test
	void addStudentForbiddenTest() throws Exception {
		StudentDto studentDto = StudentUtils.getOneStudent();
		mockMvc.perform(post("/student")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(studentDto)))
		.andExpect(status().isForbidden());
	}
}
