package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.service.EmployeeService;
import com.model.Deptarment;

@Controller
public class EmployeeConroller {

	@Autowired
	EmployeeService employeeservice;
	
	@RequestMapping(value="/home")
	public ModelAndView homepage( ModelAndView model){
		
		model.addObject("msg","Hello");
		model.setViewName("home");		
		return model;
	}
	
	@RequestMapping(value="/savedata")
	public ModelAndView savemethod(ModelAndView model, @ModelAttribute("dname") Deptarment d ){
		
		employeeservice.addCategory(d);
		
		model.setViewName("home");
		
		return model;
	}
	
	
}
