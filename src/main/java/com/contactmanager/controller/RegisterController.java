package com.contactmanager.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.contactmanager.model.Contact;

@Controller
@RequestMapping(value="/register")
@SessionAttributes("contact")
public class RegisterController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model){
		//model.addAttribute("contact", new Contact());
		model.addAttribute("contact", new Contact());
		return "template/register_info";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processFirstStage(@Valid Contact contact,BindingResult result, Model model){
		if(result.hasErrors()){
			return "template/register_info";
		}
		System.out.println(contact);
		return "template/register_contact";
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String viewSession(){
		return "template/register_contact";
	}
}
