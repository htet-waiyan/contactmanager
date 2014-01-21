package com.contactmanager.util;

import java.util.Comparator;

import com.contactmanager.model.Contact;

public class ContactFirstNameComparator implements Comparator<Contact> {

	public ContactFirstNameComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Contact c1,Contact c2) {
		// TODO Auto-generated method stub
		return c1.getFirstName().compareTo(c2.getFirstName());
	}

}
