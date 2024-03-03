package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/")
	public String welcome() {
		return "welcome.jsp";
	}

	String message = "message";

}
