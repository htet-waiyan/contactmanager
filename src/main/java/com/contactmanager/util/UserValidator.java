package com.contactmanager.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.contactmanager.model.Contact;
import com.contactmanager.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object arg0, Errors error) {
		// TODO Auto-generated method stub
		User user=(User)arg0;
		
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		String email=user.getEmail();
		String password=user.getPassword();
		
		//validation for firstName
		if(firstName==null || firstName.trim().equals("")){
			ValidationUtils.rejectIfEmpty(error, "firstName", "NotEmpty");
		}
		else{
			if(firstName.length()<2 || firstName.length()>50)
				error.rejectValue("firstName", "Size.firstName");
		}
		
		//validation for lastName
		if(lastName==null || lastName.trim().equals("")){
			ValidationUtils.rejectIfEmptyOrWhitespace(error, "lastName", "NotEmpty");
		}
		else{
			if(lastName.length()<2 || lastName.length()>50)
				error.rejectValue("lastName", "Size.firstName");
		}
		
		//validation for email
		if(email==null || email.trim().equals("")){
			ValidationUtils.rejectIfEmptyOrWhitespace(error, "email", "NotEmpty");
		}
		
		
		if(password==null || password.trim().equals("")){
			ValidationUtils.rejectIfEmptyOrWhitespace(error, "password", "NotEmpty");
		}
		else{
			if(password.length()<4)
				error.rejectValue("password", "Size.password");
		}
	}

}
