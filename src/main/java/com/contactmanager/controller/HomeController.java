package com.contactmanager.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.model.User;
import com.contactmanager.util.LoginValidator;
import com.contactmanager.util.login.LoginFailedException;
import com.contactmanager.util.login.NonRegisterEmailException;
import com.contactmanager.util.login.PasswordInvalidException;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private LoginValidator loginValidator;
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	//checking whether the current user is already authenticated or not
	private boolean isAuthenticated(){
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println(auth.getName());
		if(auth.getName().equals("anonymousUser")){
			System.out.println("Not yet authenticated");
			return false;
		}
		else{
			System.out.println("Has been authenticated");
			return true;
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		if(isAuthenticated())
			return "redirect:/contacts/all";
		
		else
			return "template/home";
	}
	
	@ExceptionHandler(NonRegisterEmailException.class)
	public ModelAndView handleNonRegisteredEmailException(NonRegisterEmailException ne){
		System.out.println("In handling method : "+ne.getErrorMsg());
		//ModelAndView modelView=new ModelAndView("template/home", "error", ne.getMessage());
		ModelAndView modelView=new ModelAndView("template/home");
		modelView.addObject("email_error", "Email is not registered.");
		return modelView;
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ModelAndView handlePasswordInvalidException(PasswordInvalidException pe){
		System.out.println("In handling method : "+pe.getErrorMsg());
		//ModelAndView modelView=new ModelAndView("template/home", "error", pe.getMessage());
		ModelAndView modelView=new ModelAndView("template/home");
		modelView.addObject("passwd_error", "Password is invalid");
		return modelView;
	}
	
	public ModelAndView handleLoginFailedException(LoginFailedException le){
		System.out.println("In handling method : "+le.getErrorMsg());
		ModelAndView modelView=new ModelAndView("template/home", "error", le.getMessage());
		return modelView;
	}
	
}
