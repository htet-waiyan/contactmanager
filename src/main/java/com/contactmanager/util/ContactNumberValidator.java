package com.contactmanager.util;

import org.springframework.stereotype.Component;

import com.contactmanager.util.contact.InvalidContactNumberException;

@Component
public class ContactNumberValidator {
	private String regex="[0-9]{4,10}";
	
	public boolean isValidNumbers(String number){
		if(number.matches(regex))
			return true;
		else
			return false;
	}
}
