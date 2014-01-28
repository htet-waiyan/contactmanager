package com.contactmanager.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.contactmanager.model.Contact;

@Component
public class ContactValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Contact.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		// TODO Auto-generated method stub
		Contact contact=(Contact) target;
		String firstName=contact.getFirstName();
		String lastName=contact.getLastName();
		
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
		
		//validating contactNumber
	}

}
