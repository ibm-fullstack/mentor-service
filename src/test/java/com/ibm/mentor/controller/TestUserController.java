package com.ibm.mentor.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.mentor.model.User;
import com.ibm.mentor.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(
		  locations = "classpath:application-test.properties")
public class TestUserController {

	@Autowired
	private MockMvc mvc;
	
    private UserRepository userRepository;
	
	@Test
	@WithMockUser
	public void getAllUsers() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/user")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$[0].name", is("Clint Mentor")));
	}
	
	@Test
	@WithMockUser
	public void getUsername() throws Exception
	{
		mvc.perform(MockMvcRequestBuilders.get("/user/{username}", "clintmarianomentor")
			      .contentType(MediaType.APPLICATION_JSON))
				  .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(content()
			      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			      .andExpect(jsonPath("$.name", is("Clinton Mariano")));
	}
	
	@Test
	@WithMockUser
	public void block() throws Exception
	{
		User user = new User();
		user.setUsername("clintmentor");
		mvc.perform( MockMvcRequestBuilders
			      .post("/user/block")
			      .content(asJsonString(user))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(print())
			      .andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void unblock() throws Exception
	{
		User user = new User();
		user.setUsername("clintmentor");
		mvc.perform( MockMvcRequestBuilders
			      .post("/user/unblock")
			      .content(asJsonString(user))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(print())
			      .andExpect(status().isOk());
	}	
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
