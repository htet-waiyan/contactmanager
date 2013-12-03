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
	public void validate(Object arg0, Errors error) {
		// TODO Auto-generated method stub
		Contact contact=(Contact)arg0;
		
		String firstName=contact.getFirstName();
		String lastName=contact.getLastName();
		
		if(firstName==null || firstName.trim().equals("")){
			ValidationUtils.rejectIfEmpty(error, "firstName", "NotEmpty");
			return;
		}
		if(firstName.length()<2 || firstName.length()>10){
			error.rejectValue("firstName", "Size.contact.firstName");
			return;
		}
	}

}
