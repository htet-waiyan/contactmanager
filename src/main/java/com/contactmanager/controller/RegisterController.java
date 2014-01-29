package com.contactmanager.controller;

import java.util.*;

import javax.servlet.http.HttpSession;
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
import com.contactmanager.service.ProfileService;
import com.contactmanager.util.UserValidator;

@Controller
@RequestMapping(value="/register")
@SessionAttributes("user")
public class RegisterController {
	
	@Autowired
	private UserValidator contactValidator;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String register(Model model){
		model.addAttribute("user", new User());
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
	public String processFirstStage(@ModelAttribute("user") @Valid User user,BindingResult result){
		if(result.hasErrors()){
			System.out.println("Validation Failed");
			return "template/register_info";
		}
		System.out.println("Validation succeed");
		System.out.println(user);
		
		Role role=new Role();
		role.setRoleName("ROLE_USER");
		
		user.setRole(role);
		profileService.addUser(user);
		
		return "/";
	}
	
	@RequestMapping(value="/contacts", method=RequestMethod.POST)
	public String processSecondStage(String txtNumber, String ddlTypes, String txtFB,
			String txtTwitter, String txtWeb,
			String addrArea,HttpSession session,RedirectAttributes ra){
		
		Profile profile=new Profile();
		Role role=new Role();
		role.setRoleName("ROLE_USER");
		
		User user=(User)session.getAttribute("user");
		System.out.println("Validation succeed");
		System.out.println(txtNumber+"-"+ddlTypes+"-"+txtFB+"-"+txtTwitter+"-"+txtWeb+"-"+addrArea);
		profile.setFacebook(txtFB);
		profile.setTwitter(txtTwitter);
		profile.setWebsite(txtWeb);
		
		profile=bindPhoneNumberToContact(profile, txtNumber, ddlTypes);
		user.setUserProfile(profile);
		user.setRole(role);
		System.out.println(user);
		System.out.println(profile);
		
		Iterator<ContactNumber> numItr=profile.getContactNumList().iterator();
		
		System.out.println("Numbers.....");
		while(numItr.hasNext()){
			ContactNumber num=numItr.next();
			System.out.println(num.getNumber());
			System.out.println(num.getContactType().getDescription());
		}
		
		profileService.addUser(user);
		
		System.out.println("Successfully registered!");
		System.out.println(profile);
		
		
		return "redirect:/dashboard";
	}
	
	private Profile bindPhoneNumberToContact(Profile profile,String number,String type){
		ContactNumber contactNumber=new ContactNumber(number);
		ContactType contactType=new ContactType(type);
		
		contactNumber.setContactType(contactType);
		
		Set<ContactNumber> phList=new HashSet<>();
		phList.add(contactNumber);
		
		profile.setContactNumList(phList);
		
		return profile;
	}
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String viewSession(){
		return "template/register_contact";
	}
}
