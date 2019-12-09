package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.StoreData;
import com.service.StoreDataService;

@Controller
public class StoreDataController {

	@Autowired
	StoreDataService storedataservice;

	@RequestMapping(value="/home")
	public String setHome(Model model){
		model.addAttribute("msg","Insert Data");
		return "home";
	}

	@RequestMapping(value="/saverec", method=RequestMethod.POST)
	public String addRecords(@ModelAttribute("save") StoreData storedata ){
		storedataservice.addRecords(storedata);
		return "home";
	}
}
