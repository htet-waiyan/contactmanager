package com.contactmanager.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	@ModelAttribute("error")
	public String initErrorMsg(){
		return null;
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "template/home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam String txtEmail,@RequestParam String txtPasswd)
	throws LoginFailedException,NonRegisterEmailException, PasswordInvalidException{
		System.out.println("Email : "+txtEmail);
		System.out.println("Password : "+txtPasswd);
		
		//varifying login credentials......
		try{
			loginValidator.varifyCredentials(txtEmail, txtPasswd);
		}
		catch(NonRegisterEmailException nre){
			System.out.println(nre.getErrorMsg());
			throw new NonRegisterEmailException(nre.getErrorCode(), nre.getErrorMsg());
		}
		catch(PasswordInvalidException pie){
			System.out.println(pie.getErrorMsg());
			throw new PasswordInvalidException(pie.getErrorCode(), pie.getErrorMsg());
		}
		catch(LoginFailedException le){
			System.out.println(le.getErrorMsg());
			throw new LoginFailedException(le.getErrorCode(), le.getErrorMsg());
		}
		
		//successful! redirect to dashboard
		return "redirect:/dashboard";
	}
	
	@ExceptionHandler(NonRegisterEmailException.class)
	public ModelAndView handleNonRegisteredEmailException(NonRegisterEmailException ne){
		System.out.println("In handling method : "+ne.getErrorMsg());
		ModelAndView modelView=new ModelAndView("template/home", "error", ne.getMessage());
		return modelView;
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ModelAndView handlePasswordInvalidException(PasswordInvalidException pe){
		System.out.println("In handling method : "+pe.getErrorMsg());
		ModelAndView modelView=new ModelAndView("template/home", "error", pe.getMessage());
		return modelView;
	}
	
	public ModelAndView handleLoginFailedException(LoginFailedException le){
		System.out.println("In handling method : "+le.getErrorMsg());
		ModelAndView modelView=new ModelAndView("template/home", "error", le.getMessage());
		return modelView;
	}
	
}
