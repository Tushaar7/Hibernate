package com.mvc.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/register")
public class MainController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String viewRegistration(Map<String,Object> model){
		
		User userForm = new User();
		model.put("userForm", userForm);
		
		List<String> professionList = new ArrayList<String>();
			
			professionList.add("Developer");
			professionList.add("Designer");
			professionList.add("IT Manager");
			model.put("professionList",professionList);
			
			return "Registrarion";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") User user, Map<String,Object> model){
		
		
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		System.out.println("birthdate: "+user.getBirthDate());
		System.out.println("pofession: "+user.getProfession());
		
		return "RegistrationSuccess";
	}
}

