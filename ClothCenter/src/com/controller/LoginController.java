package com.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.LoginModel;

/*Developed By:Raju Sable
 * Date :- 27-12-2016*/

@Controller
public class LoginController {
	

	
	/**** open login page ****/
	@RequestMapping("loginPage")
	public ModelAndView loginPage(HttpServletRequest req, Model data,@ModelAttribute("LoginModel")LoginModel loginModel)
	{
		/*String[] arr=req.getParameterValues("");
		ArrayList list= Arrays.asList(a);
*/		return new ModelAndView("login");
	}
	
	/*Login Page Authentication */
	@RequestMapping("authentication")
	public ModelAndView login(HttpSession httpSession,HttpServletRequest req,Model model,LoginModel loginModel)
	{
		try
		{
			System.out.println("above if in try");
				if(loginModel.getUsername().equals("admin") && loginModel.getPassword().equals("admin"))
				{
					System.out.println("in if admin");
					httpSession=req.getSession();
					httpSession.setAttribute("username", loginModel.getUsername());
					return new ModelAndView("HomePage");
				}
				else
				{
					System.out.println("in else admin");
					model.addAttribute("message", "Invalid Username Or Password");
					return new ModelAndView("login");	
				}
			
		}
		catch(Exception e)
		{
			model.addAttribute("message", "Invalid Username Or Password");
			return new ModelAndView("login");
		}
	}
	
	/*To Open HomePage from anywhere*/
	@RequestMapping(value="/home")
	public ModelAndView openHomePage(HttpSession httpSession,HttpServletRequest req,Model model,@ModelAttribute("LoginModel")LoginModel loginModel)
	{
		try
		{
				httpSession=req.getSession();
				String username=(String)httpSession.getAttribute("username");
				if(!username.equals(null))
				{
					return new ModelAndView("HomePage");
				}
				else
				{
					return new ModelAndView("login");	
				}
			
		}
		catch(Exception e)
		{
			return new ModelAndView("login");
		}
	}
	
	//Logout Admin from Application
	@RequestMapping("/logoutAction")
	public ModelAndView logOut(HttpServletRequest req,HttpSession httpSession,Model model,@ModelAttribute("LoginModel")LoginModel loginModel)
	{
		
			httpSession=req.getSession();
			String username=(String)httpSession.getAttribute("username");
		
			//For Logout btn
			httpSession.invalidate();
			model.addAttribute("message", "You are Successfully Logout...Login Again");
			return new ModelAndView("login");
			
	}
}