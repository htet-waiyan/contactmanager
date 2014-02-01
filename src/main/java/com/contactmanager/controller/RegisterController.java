package com.contactmanager.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.model.*;
import com.contactmanager.service.ContactService;
import com.contactmanager.service.ProfileService;
import com.contactmanager.util.UserValidator;
import com.contactmanager.util.register.EmailAlreadyExistedException;

@Controller
@RequestMapping(value="/register")
@SessionAttributes("user")
public class RegisterController {
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private ProfileService profileService;
	
	//load the form with empty user
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("user", new User());
		return "template/register_info";
	}
	
	//load contact types for dropdown selection
	@ModelAttribute("types")
	public String[] getTypes(){
		return ContactType.getTypes();
	}
	
	//registered for validation of user credentials
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(userValidator);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processFirstStage(ModelMap map,@ModelAttribute("user") @Valid User user,BindingResult result){
		if(result.hasErrors()){
			System.out.println("Validation Failed");
			return "template/register_info";
		}
		System.out.println("Validation succeed");
		System.out.println(user);
		
		Role role=new Role();
		role.setRoleName("ROLE_USER");
		
		user.setRole(role);
		
		//if the email is already existed,
		//throw EmailAlreadyExistedException 
		try{
			profileService.addUser(user);
		}
		catch(EmailAlreadyExistedException ae){
			throw new EmailAlreadyExistedException(ae.getMessage());
		}

		
		map.addAttribute("registerMsg", "You have successfully registered");
		return "register_successful";
	}
	
	//Handling for EmailAlreadyException
	@ExceptionHandler(EmailAlreadyExistedException.class)
	public ModelAndView handleEmailAlreadyException(HttpSession session,EmailAlreadyExistedException ae){
		System.out.println(ae.getMessage()+ " : Failed");
		
		ModelAndView mv=new ModelAndView("template/register_info");
		System.out.println(session.getAttribute("user"));
		
		//re-binding the registering user
		//and adding message that conveys the error.
		mv.addObject("user", session.getAttribute("user"));
		mv.addObject("emailError",ae.getMessage());
		
		return mv;
	}
}
