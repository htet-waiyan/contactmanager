package com.contactmanager.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.model.ContactType;
import com.contactmanager.model.Group;
import com.contactmanager.model.User;
import com.contactmanager.service.ContactService;
import com.contactmanager.service.ProfileService;

@Controller
@RequestMapping(value="/contacts")
@SessionAttributes(value="contact")
public class ContactsController {
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ContactService contactService;
	
	@ModelAttribute("groupNames")
	public String[] getGroupNames(){
		return Group.getGroupnames();
	}
	
	@ModelAttribute("contactType")
	public String[] getContactTypes(){
		return ContactType.getTypes();
	}
	private List<Contact> retrieveAllContacts(Integer userID){
		System.out.println("User ID :"+userID);
		
		List<Contact> contactList= profileService.getAllContactsOf(userID);
		
		return contactList;
	}
	
	private Set<ContactNumber> addNumbersFrom(Contact contact,String[] numbers,String[] types){
		ContactNumber number=null;
		ContactType type=null;
		
		Set<ContactNumber> numberSet=new TreeSet<>();
		
		for(int i=0;i<numbers.length;i++){
			System.out.println(numbers[i]+" "+types[i]);
			number=new ContactNumber(numbers[i]);
			number.setContact(contact);
			type=new ContactType(types[i]);
			
			number.setContactType(type);
			
			numberSet.add(number);
			System.out.println(number);
		}
		
		return numberSet;
	}
	
	private void editNumbersFrom(Contact contact,String[] numbers,String[] types){
		
		int count=0;
		for(ContactNumber ctNum:contact.getContactNumberSets()){
			System.out.println(ctNum.getKey());
			ctNum.setNumber(numbers[count]);
			ctNum.getContactType().setDescription(types[count]);
			
			count++;
		}
		
		if(numbers.length>count){
			ContactNumber ctNum=null;
			ContactType type=null;
			
			for(int i=count;i<numbers.length;i++){
				ctNum=new ContactNumber(numbers[i]);
				type=new ContactType(types[i]);
				
				ctNum.setContactType(type);
				ctNum.setContact(contact);
				contact.getContactNumberSets().add(ctNum);
			}
		}
		
	}
	
	private Integer getUserIDFromSession(){
		Integer userID=(Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return userID;
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public String listContacts(ModelMap map,HttpSession session){
		System.out.println("Handling /all");
		map.addAttribute("contactList", retrieveAllContacts(getUserIDFromSession()));
		return "template/contacts_list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addNewContact(ModelMap map){
		System.out.println("Forward to contact_add : ");
		map.addAttribute("contact", new Contact());
		return "template/contact_add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String saveAddingContact(@ModelAttribute("contact") Contact contact, HttpServletRequest request, HttpSession session,RedirectAttributes ra){
		System.out.println("Saving or updating contact "+contact.getContactID());
		
		
		List<Contact> contList=null;
		int userID=getUserIDFromSession();
		
		contact.setContactNumberSets(addNumbersFrom(contact,request.getParameterValues("number"), request.getParameterValues("type")));
		
		String param=request.getParameter("param").trim();
		
		System.out.println("Action Param : "+param);
		
		//if adding new contacts
		if(param.equals("add")){
			contList=profileService.addContactsTo(contact, userID);
		}
		
		//if editing existing contacts
		else{
			contList=contactService.editContact(contact, userID);
		}
		
		for(Contact c : contList){
			Set<ContactNumber> nset=c.getContactNumberSets();
			Iterator<ContactNumber> cn=nset.iterator();
			
			while(cn.hasNext()){
				ContactNumber num=cn.next();
				System.out.println(num);
			}
		}
		return "redirect:/contacts/all";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String searchContact(String txtSearch, ModelMap map,HttpSession session){
		int id=getUserIDFromSession();
		
		System.out.println("Search Query : "+txtSearch+" "+id);
		if(txtSearch!=null)
			txtSearch=txtSearch.trim();
		
		map.addAttribute("contactList",contactService.searchContacts(txtSearch, id));
		
		for(Contact c:contactService.searchContacts(txtSearch, id)){
			System.out.println(c);
		}
		
		return "template/contacts_list";
	}
	
	@RequestMapping(value="/action", method=RequestMethod.POST)
	public String deleteContacts(HttpServletRequest request,HttpSession session,String actionHidden,String toGroup,RedirectAttributes ra){
		String[] ids=request.getParameterValues("check");
		
		System.out.println("Action : "+actionHidden);
		
		if(actionHidden.trim().equalsIgnoreCase("delete")){
			//call delete service
			contactService.deleteContacts(ids);
		}
		else if(actionHidden.trim().equalsIgnoreCase("move")){
			//call moveto service
			System.out.println("Move To : "+toGroup);
			contactService.moveContactsTo(ids, toGroup.trim());
		}
		
		return "redirect:/contacts/all";
	}
	
	//no longer in use....
	/*@RequestMapping(value="list", method=RequestMethod.GET)
	public String listContactsBy(ModelMap map,String listby,HttpSession session){
		System.out.println("List contacts by "+listby);
		int id=getUserIDFromSession();
		map.addAttribute("contactList", contactService.getContactsUnder(listby,id ));
		
		for(Contact c:contactService.getContactsUnder(listby, id)){
			System.out.println("Conducting Contact List by"+listby);
			System.out.println(c);
			
			for(ContactNumber num:c.getContactNumberSets()){
				System.out.println(num.getNumber()+" "+num.getContactType().getDescription());
			}
		}
		return "template/contacts_list";
	}*/
	
	@RequestMapping(value="/{listBy}", method=RequestMethod.GET)
	public String listContact(@PathVariable(value="listBy") String list,ModelMap map,RedirectAttributes ra){
		
		
		System.out.println("List contacts by "+list);
		int id=getUserIDFromSession();
		map.addAttribute("contactList", contactService.getContactsUnder(list,id ));
		
		for(Contact c:contactService.getContactsUnder(list, id)){
			System.out.println("Conducting Contact List by"+list);
			System.out.println(c);
			
			for(ContactNumber num:c.getContactNumberSets()){
				System.out.println(num.getNumber()+" "+num.getContactType().getDescription());
			}
		}
		return "template/contacts_list";
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String viewContactDetail(String id,Model model){
		System.out.println("Viewing Contact Details : "+id);
		Contact contact=contactService.getContactDetailByID(Integer.parseInt(id.trim()));
		
		System.out.println(contact.getContactID());
		model.addAttribute("contact",contact);
		return "template/contact_add";
	}
}
