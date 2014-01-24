package com.contactmanager.service;

import java.util.List;

import com.contactmanager.model.Contact;
import com.contactmanager.model.User;

public interface ProfileService {
	public void addUser(User user);
	public List<Contact> getAllContactsOf(Integer id);
	public List<Contact> addContactsTo(Contact contact,Integer id);
	
	public List<Contact> updateContact(Contact contact,Integer id);
	
	public User getUser(String email);
}
