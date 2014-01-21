package com.contactmanager.dao;

import java.util.List;

import com.contactmanager.model.Contact;
import com.contactmanager.model.User;

public interface ProfileDAO {
	public void saveUser(User user);
	
	public User addContact(Contact contact,Integer id);
	
	public User editContact(Contact contact,Integer id);
	
	public User getUserByEmail(String email);
	
	public List<Contact> getAllContacts(Integer id);
}

