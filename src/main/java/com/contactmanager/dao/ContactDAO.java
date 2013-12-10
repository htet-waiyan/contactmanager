package com.contactmanager.dao;

import java.util.List;

import com.contactmanager.model.Contact;

public interface ContactDAO {
	//to add contact info during registration/edition
	public void addContact(Contact contact);
	
	//to retrieve emails and passwords for login verificaiton
	public List<Contact> retrieveEmailAndPassword();
}
