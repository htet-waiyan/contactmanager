package com.contactmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.contactmanager.model.Contact;
import com.contactmanager.util.ContactValidator;

@Controller
@RequestMapping(value="/register")
@SessionAttributes("contact")
public class RegisterController {
	
	@Autowired
	private ContactValidator contactValidator;
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("contact", new Contact());
		return "template/register_info";
	}
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(contactValidator);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processFirstStage(@ModelAttribute("contact") @Valid Contact contact,BindingResult result){
		if(result.hasErrors()){
			System.out.println("Validation Failed");
			return "template/register_info";
		}
		System.out.println("Validation succeed");
		System.out.println(contact);
		return "template/register_contact";
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String viewSession(){
		return "template/register_contact";
	}
}
