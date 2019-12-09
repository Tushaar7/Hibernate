package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OnBlurController {

	@RequestMapping(value="home")
	public String homePage(Model model){
		
		model.addAttribute("msg","Calculate Amount");
		
		return"home";
	}	
}
