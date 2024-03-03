package com.epam.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.epam.controller.LoginController;
import com.epam.entity.AdminAndUser;
import com.epam.service.LoginService;

@WebMvcTest(LoginController.class)
class LoginControllerTest {
    @Autowired
	private MockMvc mockMvc;
	@MockBean
	LoginService adminAndUserService;
	
	AdminAndUser admin;
	AdminAndUser user;

	@BeforeEach
	public void setUp() {
		admin = new AdminAndUser("admin", "pinky", "123");
		user = new AdminAndUser("user", "sree", "abc");
	}

	@Test
	void AdminValidationTest() throws Exception {
		Mockito.when(adminAndUserService.findMatchingUser(any())).thenReturn(true);
		mockMvc.perform(post("/admin")).andExpect(status().isOk()).andExpect(view().name("adminMenu.jsp")).andReturn();
	}

	@Test
	void AdminValidationFailTest() throws Exception {
		Mockito.when(adminAndUserService.findMatchingUser(any())).thenReturn(false);
		mockMvc.perform(post("/admin")).andExpect(status().isOk()).andExpect(view().name("failure.jsp")).andReturn();
	}

	@Test
	void addingUserTest() throws Exception {
		Mockito.when(adminAndUserService.saveUser(any())).thenReturn(true);
		mockMvc.perform(post("/user")).andExpect(view().name("userSucess.jsp")).andExpect(status().isOk())
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "user added succesfully")).andReturn();
	}

	@Test
	void addingUserExistingUserName() throws Exception {
		Mockito.when(adminAndUserService.saveUser(any())).thenReturn(false);
		mockMvc.perform(post("/user")).andExpect(view().name("failure.jsp")).andExpect(status().isOk())
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "userName already exist..!! please try with another user name"))
				.andReturn();
	}

	@Test
	void UserValidationTest() throws Exception {
		Mockito.when(adminAndUserService.findMatchingUser(any())).thenReturn(true);
		mockMvc.perform(post("/userValidation")).andExpect(status().isOk()).andExpect(view().name("userSucess.jsp"))
				.andExpect(model().attributeExists("message"))
				.andExpect(model().attribute("message", "user authenticated succesfully")).andReturn();
	}

	@Test
	void UserValidationFailTest() throws Exception {
		Mockito.when(adminAndUserService.findMatchingUser(any())).thenReturn(false);
		mockMvc.perform(post("/userValidation")).andExpect(status().isOk()).andExpect(view().name("failure.jsp"))
				.andReturn();
	}

}
