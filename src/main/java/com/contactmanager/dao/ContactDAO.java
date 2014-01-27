package com.contactmanager.dao;

import java.util.List;
import java.util.Set;

import com.contactmanager.model.Contact;
import com.contactmanager.model.ContactNumber;
import com.contactmanager.model.User;

public interface ContactDAO {
	//to add contact info during registration/edition
	public void addContact(Contact contact);
	
	public void deleteContacts(String[] contactIDs);
	
	//to retrieve emails and passwords for login verificaiton
	
	public List<Contact> getAllContacts(Integer id);
	
	public List<Contact> getContactsUnder(String groupName,Integer userID);
	
	public List<Contact> searchContacts(String query,Integer userID);
	
	public Contact getContactDetailByID(Integer id);
	
	public void moveContactsTo(String[] contIDs,String moveTo);
	
	public User editContact(Contact contact,Integer userID);
}
