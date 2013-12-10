package com.contactmanager.controller;

import java.util.*;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.model.*;
import com.contactmanager.service.ContactService;
import com.contactmanager.util.ContactValidator;

@Controller
@RequestMapping(value="/register")
@SessionAttributes("contact")
public class RegisterController {
	
	@Autowired
	private ContactValidator contactValidator;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("contact", new Contact());
		return "template/register_info";
	}
	
	@ModelAttribute("types")
	public String[] getTypes(){
		return ContactType.getTypes();
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
	
	@RequestMapping(value="/contacts", method=RequestMethod.POST)
	public String processSecondStage(@ModelAttribute("contact") Contact contact, String txtNumber, String ddlTypes, String txtFB,
			String txtTwitter, String txtWeb,
			String addrArea,BindingResult result, RedirectAttributes ra){
		if(result.hasErrors()){
			System.out.println("Validation failed");
			return "template/register_contact";
		}
		
		System.out.println("Validation succeed");
		System.out.println(txtNumber+"-"+ddlTypes+"-"+txtFB+"-"+txtTwitter+"-"+txtWeb+"-"+addrArea);
		contact.setFacebook(txtFB);
		contact.setTwitter(txtTwitter);
		contact.setWebsite(txtWeb);
		contact.setAddress(addrArea);
		
		contact=bindPhoneNumberToContact(contact, txtNumber, ddlTypes);
		
		Iterator<ContactNumber> numItr=contact.getContactNumberSets().iterator();
		
		System.out.println("Numbers.....");
		while(numItr.hasNext()){
			ContactNumber num=numItr.next();
			System.out.println(num.getNumber());
			System.out.println(num.getContactType().getDescription());
		}
		
		contactService.addContact(contact);
		
		System.out.println("Successfully registered!");
		System.out.println(contact);
		
		
		return "redirect:/dashboard";
	}
	
	private Contact bindPhoneNumberToContact(Contact cont,String number,String type){
		ContactNumber contactNumber=new ContactNumber(number);
		ContactType contactType=new ContactType(type);
		
		contactNumber.setContactType(contactType);
		
		Set<ContactNumber> phList=new HashSet<>();
		phList.add(contactNumber);
		
		cont.setContactNumberSets(phList);
		
		return cont;
	}
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String viewSession(){
		return "template/register_contact";
	}
}
