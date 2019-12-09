package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@Repository
public class RegistrationController {
	
	@RequestMapping(value="registration")
	public ModelAndView register(){
		System.out.println("Registration Controller...");
		return new ModelAndView("registration");
	}

}
