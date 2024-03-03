package com.epam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epam.dto.AdminAndUserDto;
import com.epam.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService adminAndUserService;
	
	public static final String MESSAGE = "message";
	public static final String FAILURE = "failure.jsp";
	
	@PostMapping("admin")
	public ModelAndView login(AdminAndUserDto adminDto) {
		ModelAndView modelAndView = new ModelAndView();
		adminDto.setUserType("admin");
		if (adminAndUserService.findMatchingUser(adminDto)) {
			modelAndView.setViewName("adminMenu.jsp");
		} else {
			modelAndView.setViewName(FAILURE);
		}
		return modelAndView;
	}

	@PostMapping("user")
	public ModelAndView signUp(AdminAndUserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		userDto.setUserType("user");
		if (adminAndUserService.saveUser(userDto)) {
			modelAndView.addObject(MESSAGE, "user added succesfully");
			modelAndView.setViewName("userSucess.jsp");
		} else {
			modelAndView.addObject(MESSAGE, "userName already exist..!! please try with another user name");
			modelAndView.setViewName(FAILURE);
		}

		return modelAndView;
	}

	@PostMapping("userValidation")
	public ModelAndView signIn(AdminAndUserDto userDto) {
		ModelAndView modelAndView = new ModelAndView();
		userDto.setUserType("user");
		if (adminAndUserService.findMatchingUser(userDto)) {
			modelAndView.addObject(MESSAGE, "user authenticated succesfully");
			modelAndView.setViewName("userSucess.jsp");
		} else {
			modelAndView.setViewName(FAILURE);
		}

		return modelAndView;
	}

}
