package com.contactmanager.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.model.Contact;
import com.contactmanager.util.login.LoginFailedException;
import com.contactmanager.util.login.NonRegisterEmailException;
import com.contactmanager.util.login.PasswordInvalidException;

@Component
public class LoginValidator {
	@Autowired
	private ContactDAO contactDAO;
	
	@Transactional
	public void varifyCredentials(String email,String password)throws LoginFailedException
	,NonRegisterEmailException, PasswordInvalidException{
		List<Contact> contactList=contactDAO.retrieveEmailAndPassword();
		Iterator<Contact> contactItr=contactList.iterator();
		
		Contact contact=null;
		while(contactItr.hasNext()){
			contact=contactItr.next();
			
			System.out.println(contact.getEmail()+":"+contact.getPassword());
			
			if(contact.getEmail().equals(email.trim())){
				if(contact.getPassword().equals(password.trim()))
					return;
				else{
					throw new PasswordInvalidException(10002, "Password is invalid");
				}
					
			}
			else{
				throw new NonRegisterEmailException(10001, "Email is not registered");
			}
		}
	}
}
