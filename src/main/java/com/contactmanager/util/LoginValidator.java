package com.contactmanager.util;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.contactmanager.dao.ContactDAO;
import com.contactmanager.dao.ProfileDAO;
import com.contactmanager.model.Contact;
import com.contactmanager.model.User;
import com.contactmanager.util.login.LoginFailedException;
import com.contactmanager.util.login.NonRegisterEmailException;
import com.contactmanager.util.login.PasswordInvalidException;

@Component
public class LoginValidator {
	@Autowired
	private ProfileDAO profileDAO;
	
	@Transactional
	public int varifyCredentials(String email,String password)throws LoginFailedException
	,NonRegisterEmailException, PasswordInvalidException{
		
		User user=profileDAO.getUserByEmail(email);
		
		if(user==null)
			throw new NonRegisterEmailException(10001, "Email is not registered");
		else{
			if(!user.getPassword().equals(password))
				throw new PasswordInvalidException(10002, "Password is invalid");
			else
				return user.getUserID();
		}
	}
}
